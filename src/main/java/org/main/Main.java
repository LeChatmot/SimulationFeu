    package org.main;

    import org.view.SetupView;

    import javax.swing.*;

    public class Main {

        private Main(){}

        public static void main(String[] args){
                SwingUtilities.invokeLater(() ->
                    new SetupView().setVisible(true)
                );
        }
    }
