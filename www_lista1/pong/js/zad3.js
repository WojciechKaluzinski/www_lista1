
const canvas = document.getElementById("canvas");
const gl = canvas.getContext("webgl2");
if (!gl) {
    throw "Your browser does not support WebGl";
}

const POINT_SIZE = canvas.clientHeight * 0.05;
const LINE_SIZE = canvas.clientWidth * 0.0025;
const SPEED = canvas.clientWidth * 0.233;
const ACCELERATION = 1.05;

const vertexShader = document.getElementById('2d-vertex-shader').innerText;
const fragmentShader = document.getElementById('2d-fragment-shader').innerText;

function resizeCanvasToDisplaySize(canvas, multiplier) {
    multiplier = multiplier || 1;
    const width = canvas.clientWidth * multiplier | 0;
    const height = canvas.clientHeight * multiplier | 0;
    if (canvas.width !== width || canvas.height !== height) {
        canvas.width = width;
        canvas.height = height;
        return true;
    }
    return false;
}

function createShader(gl, type, source) {
    const shader = gl.createShader(type);
    gl.shaderSource(shader, source);
    gl.compileShader(shader);
    const success = gl.getShaderParameter(shader, gl.COMPILE_STATUS);
    if (success) {
        return shader;
    }

    console.log(gl.getShaderInfoLog(shader));
    gl.deleteShader(shader);
}

function createProgramFromSources(gl, vertexSources, fragmentSources) {
    let src;
    const program = gl.createProgram();
    let shader = null;

    for (src of vertexSources) {
        shader = createShader(gl, gl.VERTEX_SHADER, src);
        gl.attachShader(program, shader);
    }

    for (src of fragmentSources) {
        shader = createShader(gl, gl.FRAGMENT_SHADER, src);
        gl.attachShader(program, shader);
    }

    gl.linkProgram(program);

    const linked = gl.getProgramParameter(program, gl.LINK_STATUS);
    if (!linked) {
        const lastError = gl.getProgramInfoLog(program);
        console.error("Error in program linking:" + lastError);
        gl.deleteProgram(program);
        return null;
    }

    return program;
}

const elements = [];

const paddleWidth = canvas.clientWidth * 0.03;
const paddleHeight = canvas.clientHeight * 0.3;

const FIGURES = {
    paddle: {
        geometry: [
            0, 0,
            paddleWidth, 0,
            0, paddleHeight,
            paddleWidth, paddleHeight,
        ],
        type: gl.TRIANGLE_STRIP,
        vertices: 4,
    },

    ball: {
        geometry: [
           -POINT_SIZE/2, -POINT_SIZE/2,
            0, -POINT_SIZE*(3/4), //3
            POINT_SIZE/2, -POINT_SIZE/2,
            POINT_SIZE*(3/4), 0, //2
            POINT_SIZE/2, POINT_SIZE/2,
            0, POINT_SIZE*(3/4), //1
            -POINT_SIZE/2, POINT_SIZE/2,
            -POINT_SIZE*(3/4), 0, //4
        ],
        type: gl.TRIANGLE_FAN,
        vertices: 8,
    },

    fieldLine: {
        geometry: [
            0, 0,
            0, canvas.clientHeight,
        ],
        type: gl.LINES,
        vertices: 2,
    },

    fieldLine2: {
        geometry: [
            -canvas.clientWidth * 0.4, canvas.clientHeight/2,
            canvas.clientWidth * 0.4, canvas.clientHeight/2,
        ],
        type: gl.LINES,
        vertices: 2,
    },

    field: {
        geometry: [
            -canvas.clientWidth * 0.45, -canvas.clientHeight * 0.5,
            canvas.clientWidth * 0.45, -canvas.clientHeight * 0.5,
            canvas.clientWidth * 0.45, canvas.clientHeight * 0.5,
            -canvas.clientWidth * 0.45, canvas.clientHeight * 0.5,
        ],
        type: gl.TRIANGLE_FAN,
        vertices: 4,
    },

    fieldCenter: {
        geometry: [
            0, canvas.clientHeight * 0.5,
        ],
        type: gl.POINTS,
        vertices: 1,
    }

};


const program = createProgramFromSources(gl, [vertexShader], [fragmentShader]);

const positionAttributeLocation = gl.getAttribLocation(program, "a_position");
const texturesCoordinationAttributeLocation = gl.getAttribLocation(program, "a_texcoord");


const resolutionUniformLocation = gl.getUniformLocation(program, "u_resolution");
const colorLocation = gl.getUniformLocation(program, "u_color");
const translationLocation = gl.getUniformLocation(program, "u_translation");
const textureUniformLocation = gl.getUniformLocation(program, "u_texture");
const uniformPointSize = gl.getUniformLocation(program, "u_pointsize");


elements.push(
    new Paddle(FIGURES.paddle.geometry,
        FIGURES.paddle.vertices,
        FIGURES.paddle.type,
        {up: 'w', down: 's'},
        null,
        [0, 0, 0, 1],
        'textures/logo.png'
    )
);
elements.push(
    new Paddle(
        FIGURES.paddle.geometry,
        FIGURES.paddle.vertices,
        FIGURES.paddle.type,
        {up: 'ArrowUp', down: 'ArrowDown'},
        [canvas.clientWidth - paddleWidth, canvas.clientHeight - paddleHeight],
        [0, 0, 0, 1],
        'textures/logo.png'
    )
);

elements.push(
    new DrawableTexture(
        FIGURES.field.geometry,
        FIGURES.field.vertices,
        FIGURES.field.type,
        [canvas.clientWidth / 2, canvas.clientHeight / 2],
        /*[0.062, 0.488, 0.023, 0],*/
        [0,0,0,0],
        'textures/background.png'
    )
);

elements.push(
    new Drawable(
        FIGURES.fieldLine.geometry,
        FIGURES.fieldLine.vertices,
        FIGURES.fieldLine.type,
        [canvas.clientWidth / 2, 0],
        [0.300, 0.023, 0.488, 0],
    )
);

elements.push(
    new Drawable(
        FIGURES.fieldLine2.geometry,
        FIGURES.fieldLine2.vertices,
        FIGURES.fieldLine2.type,
        [canvas.clientWidth / 2, 0],
        [0.300, 0.023, 0.488, 0],
    )
);

elements.push(
    new Drawable(
        FIGURES.fieldCenter.geometry,
        FIGURES.fieldCenter.vertices,
        FIGURES.fieldCenter.type,
        [canvas.clientWidth / 2, 0],
        [0.300, 0.023, 0.488, 0],
    )
);


function newBall(drawables, color) {
    const ball = new Ball(
        FIGURES.ball.geometry,
        FIGURES.ball.vertices,
        FIGURES.ball.type,
        [canvas.clientWidth / 2, canvas.clientHeight / 2],
        null,
        color,
        'textures/ball3.png'
    );
    ball.listenCollisionObject(drawables[0]);
    ball.listenCollisionObject(drawables[1]);
    drawables.push(ball);
}

newBall(elements, [0, 0, 0, 1]);

let then = 0;

function drawScene(now) {
    let element;
    now *= 0.0015;
    const deltaTime = now - then;
    then = now;

    resizeCanvasToDisplaySize(gl.canvas);

    gl.viewport(0, 0, gl.canvas.width, gl.canvas.height);

    gl.clearColor(0.0, 0.1, 0.3, 1);
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    gl.useProgram(program);


    gl.uniform2f(resolutionUniformLocation, gl.canvas.width, gl.canvas.height);

    for (element of elements) {
        element.moveAfterUpdate(deltaTime);
    }

    for (element of elements) {
        element.draw();
    }

    requestAnimationFrame(drawScene);
}

function loadTexture(gl, url) {
    const texture = gl.createTexture();
    gl.bindTexture(gl.TEXTURE_2D, texture);
    const level = 0;
    const internalFormat = gl.RGBA;
    const width = 1;
    const height = 1;
    const border = 0;
    const srcFormat = gl.RGBA;
    const srcType = gl.UNSIGNED_BYTE;
    const pixel = new Uint8Array([0, 0, 255, 255]);  // opaque blue
    gl.texImage2D(gl.TEXTURE_2D, level, internalFormat,
        width, height, border, srcFormat, srcType,
        pixel);

    const image = new Image();
    image.onload = function() {
        gl.bindTexture(gl.TEXTURE_2D, texture);
        gl.texImage2D(gl.TEXTURE_2D, level, internalFormat,
            srcFormat, srcType, image);
        if (isPowerOf2(image.width) && isPowerOf2(image.height)) {
            gl.generateMipmap(gl.TEXTURE_2D);
        } else {
            gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.CLAMP_TO_EDGE);
            gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.CLAMP_TO_EDGE);
            gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR);
        }
    };
    image.src = url;

    return texture;
}

function isPowerOf2(value) {
    return (value & (value - 1)) === 0;
}

requestAnimationFrame(drawScene);