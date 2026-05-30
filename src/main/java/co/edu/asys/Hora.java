package co.edu.asys;

public class Hora {
    //Variables
    private int horas;
    private int minutos;
    private int segundos;
    private Meridiano meridiano;
    private boolean valid;

    //Constructor
    public Hora(int horas, int minutos, int segundos, Meridiano meridiano){
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
        this.meridiano = meridiano;
        this.valid = validarHora();
    }

    //Validar hora
    public boolean validarHora(){
        //Horas
        if (this.horas < 1 || this.horas > 12) {
            return false;
        }
        //Minutos
        if (this.minutos < 0 || this.minutos > 59) {
            return false;
        }
        //Segundos
        if (this.segundos < 0 || this.segundos > 59) {
            return false;
        }
        //Meridiano
        if (this.meridiano == null) {
            return false;
        }
        return true;
    }

    //Getter y Setter
    public int getHoras() {
        return horas;
    }
    public void setHoras(int horas) {
        this.horas = horas;
    }
    public int getMinutos() {
        return minutos;
    }
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
    public int getSegundos() {
        return segundos;
    }
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    public Meridiano getMeridiano() {
        return meridiano;
    }
    public void setMeridiano(Meridiano meridiano) {
        this.meridiano = meridiano;
    }

    public Hora calcularTiempoHasta(Hora hora){
        return calcularDiferencia(hora, this);
    }
    public Hora calcularTiempoDesde(Hora hora){
        return calcularDiferencia(this, hora);
    }

    //Escrito en formato 12 horas
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d %s", this.horas, this.minutos, this.segundos, this.meridiano);
    }

    //convertir a segundos
    private int horaEnSegundos(){
        if (this.meridiano == Meridiano.AM){
            //12 AM = 00
            if (this.horas == 12){
                return (this.minutos * 60) + (this.segundos);
            } else {
                return (this.horas * 3600) + (this.minutos * 60) + (this.segundos);
            }
        } else {
            //12 PM = 12
            if (this.horas == 12) {
                return (this.horas * 3600) + (this.minutos * 60) + (this.segundos);
            } else {
                return ((this.horas + 12) * 3600) + (this.minutos * 60) + (this.segundos);
            }
        }
    }

    //convertir al formato hora desde los segundos
    private static Hora regresarDeSegundos(int segundos){
        int horas = segundos/(60*60);
        segundos = segundos%(60*60);
        int minutos = segundos/60;
        segundos = segundos%60;
        if (horas == 0){
            return new Hora(horas + 12, minutos, segundos, Meridiano.AM);
        } else if (horas == 12){
            return new Hora(horas, minutos, segundos, Meridiano.PM);
        } else if(horas > 12){
            return new Hora(horas - 12, minutos, segundos, Meridiano.PM);
        } else {
            return new Hora(horas, minutos, segundos, Meridiano.AM);
        }
    }

    //Calcular la diferencia
    public static Hora calcularDiferencia(Hora horaMayor, Hora horaMenor){
        int segundosDiferencia = horaMayor.horaEnSegundos() - horaMenor.horaEnSegundos();
        return Hora.regresarDeSegundos(segundosDiferencia);
    }
}