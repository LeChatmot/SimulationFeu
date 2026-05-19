    package org.main;

    import org.view.SetupView;

    import javax.swing.*;

    public class Main {

        private Main(){}

        static void main() {
                SwingUtilities.invokeLater(() ->
                    new SetupView().setVisible(true)
                );
        }
    }
