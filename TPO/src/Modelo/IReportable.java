package Modelo;

public interface IReportable {

    enum ReportFormat {
        TEXTO, // Usado para reportes en formato de texto plano (con títulos y saltos de línea)
        CSV    // Usado para reportes en formato Comma Separated Values (valores separados por comas)
    }

    /**
     * Genera el reporte del banco según el formato especificado.
     * Devuelve el contenido como String.
     */
    String generarReporte(ReportFormat formato);
    
}
