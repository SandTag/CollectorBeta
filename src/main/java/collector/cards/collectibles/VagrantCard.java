package collector.cards.collectibles;

import collector.actions.GainReservesAction;
import collector.powers.NextTurnReservePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class VagrantCard extends AbstractCollectibleCard {
    public final static String ID = makeID(VagrantCard.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public VagrantCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Shame();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        //this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new NextTurnReservePower(magicNumber), magicNumber));
        atb(new MakeTempCardInDrawPileAction(new Shame(), 1, false, true));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}