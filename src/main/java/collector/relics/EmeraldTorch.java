package collector.relics;

import basemod.abstracts.CustomRelic;
import collector.CollectorMod;
import collector.actions.DrawCardFromCollectionAction;
import collector.actions.GainReservesAction;
import collector.cards.Ember;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import downfall.downfallMod;
import downfall.util.TextureLoader;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static utilityClasses.Wiz.*;

public class EmeraldTorch extends CustomRelic {
    public static final String ID = CollectorMod.makeID("EmeraldTorch");
    private static final String IMG_PATH = "EmeraldTorch.png";
    private static final String OUTLINE_IMG_PATH = "EmeraldTorch.png";

    public EmeraldTorch() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.STARTER, LandingSound.MAGICAL);
        this.counter = -1;
        getUpdatedDescription();
    }

    @Override
    public void onEquip() {
        getUpdatedDescription();
    }

    @Override
    public void update(){
        super.update();
        getUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        if (downfallMod.makeCollectorWorse) {
            this.counter = 3;
        }else{
            this.counter = -1;
        }
        this.grayscale = false;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)) {
            if (!grayscale) {
               flash();
                this.grayscale = true;
                atb(new GainReservesAction(1));
           }
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter > 0) {
//            addToBot(new GainReservesAction(1));
            flash();
            this.counter--;
            DFL.atb(new DrawCardAction(1));
//            Ember em = new Ember();
//            makeInHand(em.makeCopy(), 1);
            if (this.counter < 0 && downfallMod.makeCollectorWorse){
                this.counter = 0;
            }
            if (this.counter == 0) {
                this.grayscale = true;
            }
        }
    }

    @Override
    public void onVictory(){
        this.counter = -1;
        this.grayscale = false;
    }


    @Override
    public String getUpdatedDescription() {
        if (downfallMod.makeCollectorWorse){
            return DESCRIPTIONS[1];
        }
        return DESCRIPTIONS[0];
    }
}

