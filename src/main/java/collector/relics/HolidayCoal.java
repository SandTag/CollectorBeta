package collector.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.CardModifierManager;
import collector.CollectorMod;
import collector.cardmods.ActuallyCollectedCardMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import downfall.util.TextureLoader;

public class HolidayCoal extends CustomRelic {
    public static final String ID = CollectorMod.makeID(HolidayCoal.class.getSimpleName());
    private static final String IMG_PATH = HolidayCoal.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = HolidayCoal.class.getSimpleName() + ".png";

    public HolidayCoal() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.UNCOMMON, LandingSound.MAGICAL);
        //tips.add(new CardPowerTip(new LuckyWick()));
    }

    @Override
    public void atBattleStart(){
        this.counter = 2;
        this.grayscale = false;
        beginLongPulse();
    }

    public void onCardDraw(AbstractCard card) {
        if (this.counter > 0 && card.canUpgrade() && CardModifierManager.hasModifier(card, ActuallyCollectedCardMod.ID)) {
            this.counter--;
            card.upgrade();
            if (this.counter == 0){
                this.grayscale = true;
                this.pulse=false;
            }
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
        stopPulse();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

