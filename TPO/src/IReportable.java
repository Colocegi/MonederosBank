public interface IReportable {

    enum ReportFormat { TEXTO, CSV }

    /**
     * Genera el reporte del banco según el formato especificado.
     * Devuelve el contenido como String.
     */
    String generarReporte(ReportFormat formato);
    
}
