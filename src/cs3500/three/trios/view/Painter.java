package cs3500.three.trios.view;

import cs3500.three.trios.model.card.PlayerCard;
import java.awt.Font;
import java.awt.Graphics2D;

public class Painter {

  public void paintCard(Graphics2D g2d, int x, int y, PlayerCard card) {
    String northAttackValueString = card.getNorthAttackValue().toString();
    String southAttackValueString = card.getSouthAttackValue().toString();
    String eastAttackValueString = card.getEastAttackValue().toString();
    String westAttackValueString = card.getWestAttackValue().toString();
    g2d.setFont(new Font("arial", 10, Font.PLAIN));
    g2d.drawString(northAttackValueString, x + 0.5f, y + 0.25f);
  }

  public void fillAndOutlineRect(Graphics2D g2d, int x, int y, int height, int width) {

  }
}
