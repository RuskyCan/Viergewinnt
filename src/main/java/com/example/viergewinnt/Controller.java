public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupButtonHandlers();
    }

    private void setupButtonHandlers() {
        for (int c = 0; c < model.getNumberOfColumns(); c++) {
            final int columnIndex = c;
            view.getButton(c).setOnAction(e -> handleMove(columnIndex));
        }
    }

    private void handleMove(int iColumn) {
        boolean moveSuccessful = model.Move(iColumn);

        if (moveSuccessful) {

            if (model.Winner() == null) {
                model.nextPlayer();
            }

            view.update();
        }
    }

    public void update() {
        view.update();
    }
}