package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class BlueSlaverCard extends AbstractCollectibleCard {
    public final static String ID = makeID(BlueSlaverCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 6, 4, , , , 

    public BlueSlaverCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToEnemy(m, new WeakPower(m, 1, false));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (m.hasPower(WeakPower.POWER_ID)) {
                    int x = m.getPower(WeakPower.POWER_ID).amount;
                    applyToEnemyTop(m, new WeakPower(m, x, false));
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}