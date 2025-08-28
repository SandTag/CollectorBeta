package collector.cards.collectibles;

import collector.CollectorMod;
import collector.powers.collectioncards.GremlinGangPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static collector.CollectorMod.slaverTrioCheck;
import static utilityClasses.Wiz.*;

public class BlueSlaverCard extends AbstractCollectibleCard {
    public final static String ID = makeID(BlueSlaverCard.class.getSimpleName());
    // intellij stuff attack, enemy, common, 6, 4, , , , 

    public BlueSlaverCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        //baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (m.hasPower(WeakPower.POWER_ID)) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    public void upp() {
        upgradeDamage(3);
    }

}