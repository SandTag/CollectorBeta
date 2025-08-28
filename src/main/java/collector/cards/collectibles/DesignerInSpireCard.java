package collector.cards.collectibles;

import collector.actions.DesignerInSpireAction;
import collector.cards.OnPyreCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;

public class DesignerInSpireCard extends AbstractCollectibleCard implements OnPyreCard {
    public final static String ID = makeID(DesignerInSpireCard.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , 6, 1

    public DesignerInSpireCard() {
        super(ID, 3, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
//        baseMagicNumber = magicNumber = 6;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(CardTags.HEALING);
        this.isEthereal = true;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        applyToSelf(new TorchHeadPower(4, 1));
    }

    public void upp() {
//        upgradeMagicNumber(3);
        this.isEthereal = false;
        uDesc();
    }

    @Override
    public void onPyred(AbstractCard card) {
        boolean permaUpgrade = false;
        if (card.tags.contains(expansionContentMod.KINDLING)){
            DFL.atb(new DesignerInSpireAction(true));
        }else{
            DFL.atb(new DesignerInSpireAction(false));
        }
    }
}