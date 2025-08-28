package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class JawWormCard extends AbstractCollectibleCard {
    public final static String ID = makeID(JawWormCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , 14, 4, 2, 1

    public JawWormCard() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        //baseBlock = 11;
        baseDamage = 12;
        baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToSelf(new StrengthPower(p, magicNumber));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}