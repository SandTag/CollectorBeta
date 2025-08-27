package collector.cards.collectibles;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;

public class beginningCollectible extends AbstractCollectibleCard {
    public final static String ID = makeID(beginningCollectible.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 5, 1, , , 3, 1

    public beginningCollectible() {
        super(ID, -2, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
        this.isInnate = true;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        dmg(m, AbstractGameAction.AttackEffect.FIRE);
//        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    @Override
    public void triggerOnExhaust() {
        for (AbstractMonster mon : DFL.activeMonsterList()) {
            applyToEnemy(mon, new WeakPower(mon, magicNumber, false));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
//        upgradeMagicNumber(4);
//        this.isEthereal = false;
//        uDesc();
    }
}
