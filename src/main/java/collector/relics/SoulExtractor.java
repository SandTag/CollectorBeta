package collector.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.CardModifierManager;
import collector.CollectorCollection;
import collector.CollectorMod;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.cardmods.CollectedCardMod;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import downfall.downfallMod;
import downfall.util.TextureLoader;
import utilityClasses.DFL;

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
        AbstractDungeon.player.energy.energyMaster++;
        getUpdatedDescription();
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    @Override
    public void update(){
        super.update();
        getUpdatedDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        if (!downfallMod.makeCollectorWorse) {
            if (CardModifierManager.hasModifier(card, CollectedCardMod.ID) || CardModifierManager.hasModifier(card, ActuallyCollectedCardMod.ID)) {
                PurgeField.purge.set(card, true);
                card.exhaust = false;
                card.exhaustOnUseOnce = false;
            }
        }
    }

    @Override
    public boolean canSpawn() {
        return !CollectorCollection.collection.isEmpty();
    }

    @Override
    public String getUpdatedDescription() {
        if (downfallMod.makeCollectorWorse){
            return DESCRIPTIONS[1];
        }
        return DESCRIPTIONS[0];
    }
}
