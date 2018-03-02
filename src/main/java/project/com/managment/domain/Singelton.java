package project.com.managment.domain;


class Singelton {
    private static Singelton instance = null;
    protected Singelton() {
        // Exists only to defeat instantiation.
    }
    public static Singelton getInstance() {
        if(instance == null) {
            instance = new Singelton();
        }
        return instance;
    }
}