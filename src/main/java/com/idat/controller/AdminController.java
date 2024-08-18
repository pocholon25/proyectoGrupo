package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.idat.model.Categoria;
import com.idat.model.Producto;
import com.idat.model.Venta;
import com.idat.service.CategoriaServicio;
import com.idat.service.ProductService;
import com.idat.service.VentaService;

import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private ProductService productService;

    @Autowired
    private VentaService ventaService;

    @GetMapping("/")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/addproducto")
    public String addProducto(Model model, HttpSession session) {
        // Agregar producto y otras entidades al modelo
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaServicio.getAllCategoria());
        model.addAttribute("productos", productService.getAllProductos());

        // Obtener y eliminar mensajes de sesión
        if (session.getAttribute("succMsg") != null) {
            model.addAttribute("succMsg", session.getAttribute("succMsg"));
            session.removeAttribute("succMsg");  // Eliminar el mensaje después de mostrarlo
        }
        if (session.getAttribute("errorMsg") != null) {
            model.addAttribute("errorMsg", session.getAttribute("errorMsg"));
            session.removeAttribute("errorMsg");  // Eliminar el mensaje después de mostrarlo
        }

        return "admin/addproducto";  // Nombre del archivo Thymeleaf para mostrar el formulario
    }


    @GetMapping("/categoria")
    public String categoria(Model model) {
        model.addAttribute("categorias", categoriaServicio.getAllCategoria());
        return "admin/categoria";
    }

    @PostMapping("/savecategoria")
    public String saveCategoria(@ModelAttribute Categoria categoria, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file != null ? file.getOriginalFilename() : "default.jpg";
        categoria.setImageName(imageName);

        Boolean existCategoria = categoriaServicio.existsByNombre(categoria.getNombre());

        if (existCategoria) {
            session.setAttribute("errorMsg", "El nombre de la Categoría ya existe");
        } else {
            Categoria saveCategoria = categoriaServicio.saveCategoria(categoria);

            if (ObjectUtils.isEmpty(saveCategoria)) {
                session.setAttribute("errorMsg", "No se pudo guardar. Error interno del servidor");
            } else {
                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "categoria_img" + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                session.setAttribute("successMsg", "Registro Satisfactorio");
            }
        }
        return "redirect:/admin/categoria";
    }

    @GetMapping("/deleteCategoria/{id}")
    public String deleteCategoria(@PathVariable Long id, HttpSession session) {
        Boolean deleteCategoria = categoriaServicio.deleteCategoria(id);
        if (deleteCategoria) {
            session.setAttribute("successMsg", "Categoria eliminada exitosamente");
        } else {
            session.setAttribute("errorMsg", "Error en el servidor");
        }
        return "redirect:/admin/categoria";
    }

    @GetMapping("/loadEditCategoria/{id}")
    public String loadEditCategoria(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaServicio.getCategoriaById(id));
        return "admin/edit_categoria";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Producto product, @RequestParam("file") MultipartFile image, HttpSession session) throws IOException {
        String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();
        product.setImage(imageName);

        Producto saveProduct = productService.saveProducto(product);

        if (!ObjectUtils.isEmpty(saveProduct)) {
            File saveFile = new ClassPathResource("static/img").getFile();
            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "product_img" + File.separator + image.getOriginalFilename());
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            session.setAttribute("succMsg", "Producto guardado correctamente");
        } else {
            session.setAttribute("errorMsg", "Se genero un error");
        }

        return "redirect:/admin/addproducto";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session) {
        Boolean deleteProduct = productService.deleteProducto(id);
        if (deleteProduct) {
            session.setAttribute("succMsg", "Producto eliminado correctamente");
        } else {
            session.setAttribute("errorMsg", "Error al eliminar");
        }
        return "redirect:/admin/addproducto";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, Model m) {
        m.addAttribute("product", productService.getProductoById(id));
        m.addAttribute("categories", categoriaServicio.getAllCategoria());
        return "admin/edit_product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Producto product, @RequestParam("file") MultipartFile image, HttpSession session) throws IOException {
        String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();
        product.setImage(imageName);

        Producto updatedProduct = productService.updateProducto(product, image);

        if (updatedProduct != null) {
            session.setAttribute("succMsg", "Producto actualizado exitosamente");
        } else {
            session.setAttribute("errorMsg", "Algo salió mal en el servidor");
        }

        return "redirect:/admin/edit/" + product.getId();
    }

    @GetMapping("/productos")
    public String listProducts(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String categoria) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> productos;

        if (categoria != null && !categoria.isEmpty()) {
            productos = productService.getProductosPaginadosPorCategoria(pageable, categoria);
        } else {
            productos = productService.getProductosPaginados(pageable);
        }

        model.addAttribute("productos", productos.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productos.getTotalPages());

        return "admin/productos";
    }

    @GetMapping("/listaPagina")
    public Page<Venta> listarPagina(Pageable pag) {
        return ventaService.listarPagina(pag);
    }

    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.listar();
        model.addAttribute("ventas", ventas);
        return "admin/ventas";
    }

    @GetMapping("/ventas/{id}")
    public String verDetalleVenta(@PathVariable("id") Long id, Model model) {
        List<Venta> ventas = ventaService.listar();
        model.addAttribute("ventas", ventas);
        Venta venta = ventaService.buscar(id);
        model.addAttribute("venta", venta);
        model.addAttribute("cliente", venta.getCliente());
        model.addAttribute("detalles", venta.getDetalleVenta());
        return "admin/detalle_venta";
    }
}
