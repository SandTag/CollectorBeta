package collector.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class WhirlingFlame extends AbstractCollectorCard {
    public final static String ID = makeID(WhirlingFlame.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 12, 4, , , 2, 1

    public WhirlingFlame() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new DiscardPileToTopOfDeckAction(p));
    }

    public void upp() {
        upgradeDamage(3);
    }
}