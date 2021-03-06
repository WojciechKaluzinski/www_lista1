class Paddle extends DrawableTexture {
    constructor(geometry, verticesNumber, type, keys, translation, color, textureUrl) {
        super(geometry, verticesNumber, type, translation, color, textureUrl);

        this.moveVector = [0, 1];
        this.keys = keys;

        this.keysPressed = {};

        window.addEventListener('keydown', (event) => {
            this.keysPressed[event.key] = true;
        });
        window.addEventListener('keyup', (event) => {
            this.keysPressed[event.key] = false;
        });
    }

    moveAfterUpdate(deltaTime) {
        if (this.keysPressed[this.keys.up] === true) {
            this.translation[1] += SPEED * deltaTime;
        }
        if (this.keysPressed[this.keys.down] === true) {
            this.translation[1] -= SPEED * deltaTime;
        }
    }
}