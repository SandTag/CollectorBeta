package collector.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static collector.CollectorMod.makeID;

public class CollectedCardMod extends AbstractCardModifier {
    public static final String ID = makeID("CollectedCardMod");
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private boolean hadExhaust = false;

    @Override
    public void onInitialApplication(AbstractCard card) {
//        hadExhaust = card.exhaust; - Collectibles no longer default to exhausting on play.
//        card.exhaust = true;
        CardModifierManager.addModifier(card, new ActuallyCollectedCardMod());
    }

    @Override
    public void onRemove(AbstractCard card) {
//        card.exhaust = hadExhaust;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CollectedCardMod();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[0] + rawDescription;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }
}
