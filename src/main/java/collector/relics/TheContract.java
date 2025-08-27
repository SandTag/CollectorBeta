package collector.relics;

import basemod.AutoAdd;
import basemod.abstracts.CustomRelic;
import basemod.helpers.CardModifierManager;
import collector.CollectorMod;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.cardmods.CollectedCardMod;
import collector.cards.collectibles.CollectorCard;
import collector.patches.CollectiblesPatches.CollectibleCardColorEnumPatch;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import downfall.util.TextureLoader;
import expansioncontent.actions.SpecialClauseDiscoveryAction;
import hermit.util.Wiz;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

import java.util.ArrayList;

public class TheContract extends CustomRelic {
    public static final String ID = CollectorMod.makeID(TheContract.class.getSimpleName());
    private static final String IMG_PATH = TheContract.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = TheContract.class.getSimpleName() + ".png";
    private ArrayList<AbstractCard> cards = new ArrayList<>();

    public TheContract() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.SHOP, LandingSound.MAGICAL);
        dontCollectSelf();
    }
    private void dontCollectSelf(){
        cards.removeIf(c -> c instanceof CollectorCard);
    }

    public void atBattleStartPreDraw() {
        refresh();
        DFL.atb(new LaterAction(() -> {
            ArrayList<AbstractCard> card = new ArrayList<AbstractCard>() {{
                AbstractCard cardA = cards.get(AbstractDungeon.relicRng.random(cards.size() - 1));
                cards.remove(cardA);
                add(cardA);

                AbstractCard cardB = cards.get(AbstractDungeon.relicRng.random(cards.size() - 1));
                cards.remove(cardB);
                add(cardB);

                AbstractCard cardC = cards.get(AbstractDungeon.relicRng.random(cards.size() - 1));
                cards.remove(cardC);
                add(cardC);
            }};
            card.forEach(c -> {
                CardModifierManager.addModifier(c, new CollectedCardMod());
                CardModifierManager.addModifier(c, new ActuallyCollectedCardMod());
            });
            DFL.atb(new SpecialClauseDiscoveryAction(card, false));
        }));
        DFL.atb(new RelicAboveCreatureAction(DFL.pl(), this));
    }

    private void refresh(){
        cards.clear();
    cards = Wiz.getCardsMatchingPredicate(c -> c.color == CollectibleCardColorEnumPatch.CardColorPatch.COLLECTIBLE && !c.hasTag(AbstractCard.CardTags.HEALING), true);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

