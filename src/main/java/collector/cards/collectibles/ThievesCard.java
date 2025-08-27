package collector.cards.collectibles;

import collector.powers.collectioncards.ThievesCardPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ThievesCard extends AbstractCollectibleCard {
    public final static String ID = makeID(ThievesCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 1, 1

    public ThievesCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new SmokeBombEffect(DFL.pl().hb.cX, DFL.pl().hb.cY)));
        //applyToSelf(new ThievesCardPower(magicNumber));
        if (!upgraded){
            applyToEnemy(m, new StrengthPower(m, -1));
            applyToSelf(new StrengthPower(p, 1));
        }else{
            for (AbstractMonster mo : DFL.activeMonsterList()){
                applyToEnemy(m, new StrengthPower(mo, -1));
                applyToSelf(new StrengthPower(p, 1));
            }
        }
    }

    public void upp() {
        //upgradeMagicNumber(2);
        this.target = CardTarget.ALL_ENEMY;
        uDesc();
    }
}