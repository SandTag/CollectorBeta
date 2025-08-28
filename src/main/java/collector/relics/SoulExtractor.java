package collector.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.CardModifierManager;
import collector.CollectorCollection;
import collector.CollectorMod;
import collector.actions.GainReservesAction;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.cardmods.CollectedCardMod;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import downfall.downfallMod;
import downfall.util.TextureLoader;
import utilityClasses.DFL;

import java.util.ArrayList;

public class SoulExtractor extends CustomRelic {
    public static final String ID = CollectorMod.makeID(SoulExtractor.class.getSimpleName());
    private static final String IMG_PATH = SoulExtractor.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = SoulExtractor.class.getSimpleName() + ".png";

    public SoulExtractor() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
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
    public void atTurnStart(){
        if (downfallMod.makeCollectorWorse){
            if (collectionUnique()){
                DFL.atb(new DrawCardAction(2));
            }
        }else{
            if (collectionUnique()){
                DFL.atb(new GainEnergyAction(1));
            }
        }
    }

    private boolean collectionUnique(){
        ArrayList<String> strings = new ArrayList<>();
        for (AbstractCard card : CollectorCollection.collection.group){
            if (strings.contains(card.name)){
                return false;
            }
            strings.add(card.name);
        }
        return true;
    }

    @Override
    public String getUpdatedDescription() {
        if (downfallMod.makeCollectorWorse){
            return DESCRIPTIONS[1];
        }
        return DESCRIPTIONS[0];
    }
}
