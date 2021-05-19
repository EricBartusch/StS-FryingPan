package fryingPan.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import fryingPan.DefaultMod;
import fryingPan.patches.CardTagEnum;
import fryingPan.util.TextureLoader;

import static com.megacrit.cardcrawl.cards.AbstractCard.*;
import static fryingPan.DefaultMod.makeRelicOutlinePath;
import static fryingPan.DefaultMod.makeRelicPath;

public class FryingPan extends CustomRelic {

    public static final String ID = DefaultMod.makeID("FryingPan");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic2.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic2.png"));

    public FryingPan() { super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.SOLID); }

    @Override
    public void onEquip() {
        for (AbstractCard c: AbstractDungeon.player.masterDeck.getAttacks().group) {
            if(!c.hasTag(CardTags.STRIKE)) {
                c.name = c.name + " " + CardLibrary.getCard("Strike_R").name;
                c.tags.add(CardTagEnum.FRYING_STRIKE);
            }
        }
    }

    @Override
    public void onUnequip() {
        for (AbstractCard c: AbstractDungeon.player.masterDeck.getAttacks().group) {
            if(c.hasTag(CardTagEnum.FRYING_STRIKE)) {
                c.name = c.originalName;
            }
        }
    }

    @Override
    public void onPreviewObtainCard(AbstractCard c) {
        if (c.type.equals(CardType.ATTACK) && !c.hasTag(CardTags.STRIKE)) {
            c.name = c.name + " " + CardLibrary.getCard("Strike_R").name;
            c.tags.add(CardTagEnum.FRYING_STRIKE);
        }
    }

    @Override
    public void atPreBattle() {
        for (AbstractCard c: AbstractDungeon.player.drawPile.getAttacks().group) {
            c.tags.add(CardTags.STRIKE);
        }
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        return c.tags.indexOf(CardTags.STRIKE) != c.tags.lastIndexOf(CardTags.STRIKE) ? damage + 1.0F : damage;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
