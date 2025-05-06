package collector.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;

public class BurningStrike extends AbstractCollectorCard {
    public final static String ID = makeID(BurningStrike.class.getSimpleName());
    // intellij stuff attack, enemy, special, 21, 6, , , , 

    public BurningStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 2;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(5);
//        upgradeMagicNumber(1);
        uDesc();
    }
}
