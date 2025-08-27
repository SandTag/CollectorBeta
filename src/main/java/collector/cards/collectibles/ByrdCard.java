package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;

public class ByrdCard extends AbstractCollectibleCard {
    public final static String ID = makeID(ByrdCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 1, 1, , , , 

    public ByrdCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 1;
        baseMagicNumber = magicNumber = 5;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
//        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    public void upp() {
        upgradeDamage(1);
        uDesc();
    }
}