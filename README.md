# InvMove
### Minecraft Forge mod that adds the ability to walk around while in inventories

![](demo/InvMove-0.2.0.gif)

Allows for moving, jumping, sprinting, etc. from within inventories.

Also disables the darkened background in inventories that don't pause the game.

Both features should work with all vanilla inventories, and can be toggled on or off per-inventory in the config menu.

This mod is client-side, but it may raise alarms if used on servers with anticheat.
I take no responsibility if you get banned or something because you used this on public servers.

Made after [I couldn't find an existing mod for this](https://redd.it/egwe8w).

### [Releases](https://github.com/PieKing1215/InvMove/releases)

Requires [Cloth Config](https://www.curseforge.com/minecraft/mc-mods/cloth-config-forge) so the config screen works.

## Usage

Feel free to use in packs if you wish (though compatibility hasn't been tested thoroughly)

Tested on Forge 31.2.0 with MC 1.15.2, on both singleplayer and multiplayer.

The mod is licensed under the [GNU General Public License v3.0](LICENSE.md)

## TODO
- ~~Make it work with more inventories.~~ Implemented in 0.2.0
- ~~Add usage/license info~~ GPL3
- ~~Refactor onGuiOpen to reduce redundancy/make it more general~~ New background disable method
- Refactor allowMovementInScreen to reduce redundancy/make it more general
- Test/check/fix multiplayer/server compatibility
- Test/check/fix mod compatibility
