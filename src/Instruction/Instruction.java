package Instruction;

import city.cs.engine.SoundClip;
import game.Game;
import game.InstructionBackground;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Instruction {
    private JPanel instructionPanel;
    private JButton returnButton;
    private SoundClip clip;


    public Instruction(Game game) {

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.returnFromInstructions();
                try {
                    clip = new SoundClip("sound/ClickSound.wav");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                clip.play();
            }
        });
    }

    public InstructionBackground getInstructionPanel() {
        return (InstructionBackground) this.instructionPanel;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        instructionPanel = new InstructionBackground();

    }
}
