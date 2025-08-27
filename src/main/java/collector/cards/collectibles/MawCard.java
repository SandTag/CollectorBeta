package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;

public class MawCard extends AbstractCollectibleCard {
    public final static String ID = makeID(MawCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 40, 10, , , , 

    public MawCard() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 24;
        baseMagicNumber = magicNumber = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToSelf(new StrengthPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeDamage(8);
        upgradeMagicNumber(1);
    }
}