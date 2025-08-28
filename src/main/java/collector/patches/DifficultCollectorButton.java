package collector.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import downfall.downfallMod;
import java.util.ArrayList;

import static reskinContent.patches.CharacterSelectScreenPatches.allTextInfoX;

public class DifficultCollectorButton {
    public static final Hitbox challengeIncreaseButton = new Hitbox(40.0f * Settings.scale * (0.01f + (1.0f - 0.019f)), 40.0f * Settings.scale);

    public static final ArrayList<PowerTip> challengeTips = new ArrayList<>();
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("collector:ChallengeUIButton");

    @SpirePatch(clz = CharacterOption.class, method = "renderRelics")
    public static class RenderBtn {
        public static void Postfix(CharacterOption obj, SpriteBatch sb) {
            if ( (obj.name.toLowerCase().contains("the collector") || obj.name.contains("le collecteur") || obj.name.contains("수집가") || obj.name.contains("Коллекционер") || obj.name.contains("收藏家") ) && obj.selected) {
                challengeIncreaseButton.move(190.0f * Settings.scale, Settings.HEIGHT / 2.0f - 190.0f * Settings.scale);
                challengeIncreaseButton.render(sb);

                sb.setColor(Color.WHITE);
                sb.draw(ImageMaster.CHECKBOX, challengeIncreaseButton.cX - 32.0f + allTextInfoX, challengeIncreaseButton.cY - 32.0f , 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale * (0.01f + (1.0f - 0.019f)), Settings.scale * (0.01f + (1.0f - 0.019f)), 0.0f, 0, 0, 64, 64, false, false);
                if (!downfallMod.makeCollectorWorse) {//Collector has not been made worse.
                    sb.draw(ImageMaster.TICK, challengeIncreaseButton.cX - 32.0f + allTextInfoX, challengeIncreaseButton.cY - 32.0f , 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale * (0.01f + (1.0f - 0.019f)), Settings.scale * (0.01f + (1.0f - 0.019f)), 0.0f, 0, 0, 64, 64, false, false);
                    System.out.println("Current State: " + downfallMod.makeCollectorWorse);
                }
                FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, uiStrings.TEXT[0], challengeIncreaseButton.cX + 25f * Settings.scale + allTextInfoX, challengeIncreaseButton.cY, Settings.BLUE_TEXT_COLOR);
            }
        }
    }

    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
    public static class UpdateHitbox {
        public static void Postfix(CharacterOption obj) {
            if ((obj.name.toLowerCase().contains("the collector") || obj.name.contains("le collecteur") || obj.name.contains("수집가") || obj.name.contains("Коллекционер") || obj.name.contains("收藏家") ) && obj.selected) {
                challengeIncreaseButton.update();
                if (challengeIncreaseButton.hovered) {
                    if (challengeTips.isEmpty()) {
                        challengeTips.add(new PowerTip( uiStrings.TEXT[0],  uiStrings.TEXT[1]));
                    }
                    if (InputHelper.mX < 1400.0f * Settings.scale) {
                        TipHelper.queuePowerTips(InputHelper.mX + 60.0f * Settings.scale, InputHelper.mY - 50.0f * Settings.scale, challengeTips);
                    } else {
                        TipHelper.queuePowerTips(InputHelper.mX - 350.0f * Settings.scale, InputHelper.mY - 50.0f * Settings.scale, challengeTips);
                    }

                    if (InputHelper.justClickedLeft) {
                        CardCrawlGame.sound.playA("UI_CLICK_1", -0.4f);
                        challengeIncreaseButton.clickStarted = true;
                    }
                    if (challengeIncreaseButton.clicked) {
                        downfallMod.makeCollectorWorse = !downfallMod.makeCollectorWorse;
                        System.out.println("Current State: " + downfallMod.makeCollectorWorse);
                        challengeIncreaseButton.clicked = false;
                        downfallMod.saveData();//Actually saves this time!
                        System.out.println("Current State: " + downfallMod.makeCollectorWorse);
                    }
                }
            }
        }
    }

}
