package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;
import static utilityClasses.Wiz.applyToEnemy;
import static collector.CollectorMod.makeID;

public class AcidSlimeCard extends AbstractCollectibleCard {
    public final static String ID = makeID(AcidSlimeCard.class.getSimpleName());
    // intellij stuff skill, enemy, common, , , , , , 

    public AcidSlimeCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseDamage = 4;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new RemoveAllBlockAction(m, p));
        dmg(m, AbstractGameAction.AttackEffect.POISON);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }


    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
//        uDesc();
    }
}