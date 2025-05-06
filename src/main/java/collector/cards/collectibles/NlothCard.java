package collector.cards.collectibles;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.util.CardIgnore;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

@NoCompendium @NoPools @CardIgnore @Deprecated
public class NlothCard extends AbstractCollectibleCard {
    public final static String ID = makeID(NlothCard.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public NlothCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard toGenerate = AbstractDungeon.srcRareCardPool.getRandomCard(AbstractDungeon.cardRandomRng).makeCopy();
        if (upgraded) {
            toGenerate.updateCost(-999);
        }
        else {
            toGenerate.updateCost(-1);
        }
        makeInHand(toGenerate);
    }

    public void upp() {
        uDesc();
    }
}