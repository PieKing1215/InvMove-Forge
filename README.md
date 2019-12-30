# InvMove
### Minecraft Forge mod that adds the ability to walk around while in inventories

![](demo/InvMove-0.2.0.gif)

Allows for moving, sprinting, jumping, etc. from within inventories.

Also disables the darkened background in inventories that don't pause the game.

Both features should work with all vanilla inventories, and can be toggled on or off per-inventory in the config file.

Made after [I couldn't find an existing mod for this](https://redd.it/egwe8w).

### [Releases](https://github.com/PieKing1215/InvMove/releases)

## Usage

Feel free to use in packs if you wish (though it is currently untested with other mods)

It's only tested on Forge 28.1.0 with MC 1.14.4, on a singleplayer world with no other mods

The mod is licensed under the [GNU General Public License v3.0](LICENSE.md)

## TODO
- ~~Make it work with more inventories.~~ Implemented in 0.2.0
- ~~Add usage/license info~~ GPL3
- Refactor allowMovementInScreen and onGuiOpen to reduce redundancy/make it more general
- Test/check/fix multiplayer/server compatibility
- Test/check/fix mod compatibility
- Test/check/fix version compatibility
