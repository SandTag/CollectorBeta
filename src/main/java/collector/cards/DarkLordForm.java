package collector.cards;

import basemod.helpers.BaseModCardTags;
import collector.effects.GreenThirdEyeEffect;
import collector.powers.DarkLordFormPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class DarkLordForm extends AbstractCollectorCard {
    public final static String ID = makeID(DarkLordForm.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , , 

    public DarkLordForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        tags.add(BaseModCardTags.FORM);
        this.baseMagicNumber = magicNumber = 6;
        this.baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new GreenThirdEyeEffect(p.hb.cX, p.hb.cY)));
        if (!DFL.pl().hasPower(DarkLordFormPower.POWER_ID)) {
            applyToSelf(new DarkLordFormPower(magicNumber, secondMagic));
        }else{
            DarkLordFormPower playerPower = (DarkLordFormPower)DFL.pl().getPower(DarkLordFormPower.POWER_ID);
            playerPower.stackCorrectly(magicNumber, secondMagic);
        }
    }

    public void upp() {
        upgradeMagicNumber(3);
        upgradeSecondMagic(1);
        //upgradeBaseCost(3);
//        uDesc();
    }
}