package co.edu.asys;

public class Fecha {

    private int dia;
    private int mes;
    private int anio;
    private boolean valid;

    //Constructor
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.valid = validarFecha();
    }

    //Validar fecha
    public boolean validarFecha() {
        //Año
        if (this.anio <= 0) {
            return false;
        }
        //Mes en general
        if (this.mes < 1 || this.mes > 12) {
            return false;
        }
        //Febrero
        if (this.mes == 2 && esBisiesto(this.anio)){
            return this.dia <= 29;
        } else if (this.mes == 2 && !esBisiesto(this.anio)){
            return this.dia <= 28;
        }
        //Mes de 30 dias
        if (this.mes == 4 || this.mes == 6 || this.mes == 9 || this.mes == 11) {
            return this.dia <= 30;
        }
        //Validar dia
        if (this.dia < 1 || this.dia > 31) {
            return false;
        }
        return true;
    }
    // VALIDAR AÑO BISIESTO
    public static boolean esBisiesto(int anio){
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }

    //Getter y Setter
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Fecha calcularTiempoHasta(Fecha fecha){
        return calcularDiferencia(fecha, this);
    }
    public Fecha calcularTiempoDesde(Fecha fecha){
        return calcularDiferencia(this, fecha);
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", this.dia, this.mes, this.anio);
    }

    //Calcular dia de la semana
    public DiaSemana calcularDiaSemana(){
        int diasTotales = this.fechaEnDias();
        int residuo = (diasTotales - 1) % 7;
        return switch (residuo) {
            case 0 -> DiaSemana.LUNES;
            case 1 -> DiaSemana.MARTES;
            case 2 -> DiaSemana.MIERCOLES;
            case 3 -> DiaSemana.JUEVES;
            case 4 -> DiaSemana.VIERNES;
            case 5 -> DiaSemana.SABADO;
            default -> DiaSemana.DOMINGO;
        };
    }
    //Calcular mes del año
    public NombreMes obtenerNombreMes(){
        return switch (this.mes) {
            case 1 -> NombreMes.ENERO;
            case 2 -> NombreMes.FEBRERO;
            case 3 -> NombreMes.MARZO;
            case 4 -> NombreMes.ABRIL;
            case 5 -> NombreMes.MAYO;
            case 6 -> NombreMes.JUNIO;
            case 7 -> NombreMes.JULIO;
            case 8 -> NombreMes.AGOSTO;
            case 9 -> NombreMes.SEPTIEMBRE;
            case 10 -> NombreMes.OCTUBRE;
            case 11 -> NombreMes.NOVIEMBRE;
            default -> NombreMes.DICIEMBRE;
        };
    }

    //Convertir a Dias
    private int fechaEnDias(){
        int diasTotales = this.dia;
        //Sumar dias en cada mes
        for (int i = 1; i < this.mes; i++){
            diasTotales += diasEnMes(i, this.anio);
        }
        //Sumar dias en cada año completo
        for (int i = 1; i < this.anio; i++){
            if (esBisiesto(i)){
                diasTotales += 366;
            } else {
                diasTotales += 365;
            }
        }
        return diasTotales;
    }

    //Regresar desde solo dias
    private static Fecha regresarDeDias(int dias){
        int anio = 1;
        //Calcular cuantos años
        while (true){
            int diasAnio = esBisiesto(anio) ? 366 : 365;
            if (dias > diasAnio){
                dias -= diasAnio;
                anio++;
            } else {
                break;
            }
        }
        int mes = 1;
        //Calcular cuantos meses
        while (true){
            int diasMes = diasEnMes(mes, anio);
            if (dias > diasMes){
                dias -= diasMes;
                mes++;
            } else {
                break;
            }
        }
        //Dias restantes y fecha final
        return new Fecha(dias, mes, anio);
    }

    private static int diasEnMes(int mes, int anio){
        if (mes == 2){
            return esBisiesto(anio) ? 29 : 28;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11){
            return 30;
        } else {
            return 31;
        }
    }
    
    //Calcular la diferencia entre fechas
    public static Fecha calcularDiferencia(Fecha fechaMayor, Fecha fechaMenor){
        int diasDiferencia = fechaMayor.fechaEnDias() - fechaMenor.fechaEnDias();
        return Fecha.regresarDeDias(diasDiferencia);
    }
}