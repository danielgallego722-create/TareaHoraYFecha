package co.edu.asys;

public enum NombreMes {
    ENERO,
    FEBRERO,
    MARZO,
    ABRIL,
    MAYO,
    JUNIO,
    JULIO,
    AGOSTO,
    SEPTIEMBRE,
    OCTUBRE,
    NOVIEMBRE,
    DICIEMBRE;

    @Override
    public String toString() {
        return switch (this) {
            case ENERO -> "Enero";
            case FEBRERO -> "Febrero";
            case MARZO -> "Marzo";
            case ABRIL -> "Abril";
            case MAYO -> "Mayo";
            case JUNIO -> "Junio";
            case JULIO -> "Julio";
            case AGOSTO -> "Agosto";
            case SEPTIEMBRE -> "Septiembre";
            case OCTUBRE -> "Octubre";
            case NOVIEMBRE -> "Noviembre";
            default -> "Diciembre";
        };
    }
}