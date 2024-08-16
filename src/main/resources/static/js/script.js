document.addEventListener("DOMContentLoaded", function() {
    // Eliminar mensajes de éxito después de 5 segundos
    var successMessage = document.querySelectorAll('.text-success');
    successMessage.forEach(function(message) {
        setTimeout(function() {
            message.remove();
        }, 5000);
    });
    
    // Eliminar mensajes de error después de 5 segundos
    var errorMessage = document.querySelectorAll('.text-danger');
    errorMessage.forEach(function(message) {
        setTimeout(function() {
            message.remove();
        }, 5000);
    });
});
