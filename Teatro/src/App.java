public class App {
    private static Menu menu = new Menu();

    public static void next() {
        switch (menu.getNextMenu()) {
            case "!":
                menu.menuInicial();
                break;
            case "0":
                menu.menuPrincipal();
                break;
            case "0-1":
                menu.menuCadastro();
                break;
            case "0-2":
                menu.menuConsulta();
                break;
            case "0-3":
                menu.menuRemocao();
                break;
            default:
                menu.menuErro();
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        // MenuControl menc = new MenuControl();

        // while (!menc.shouldStop()) {
        //     menc.next();
        // }
    }
}
