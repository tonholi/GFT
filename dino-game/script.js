const dino = document.querySelector('.dino');
const background = document.querySelector('.background');
const Score = document.querySelector('.Score')
let isJumping = false;
let position = 0;
var pontos = 0;

function handleKeyUp(event) {
    if (event.keyCode === 32) {
        if (!isJumping) {
            jump();
        }
    }
}

function jump() {
    isJumping = true;
    let upInterval = setInterval(() => {
        if (position >= 150) {
            clearInterval(upInterval);
            //Descendo
            let dowInterval = setInterval(() => {
                if (position <= 0) {
                    clearInterval(dowInterval);
                    isJumping = false;
                } else {
                    position -= 20;
                    dino.style.bottom = position + 'px';
                }
            }, 20);
        } else {
            //Subindo
            position += 20;
            dino.style.bottom = position + 'px';
        }
    }, 20);
}

function createCactus() {
    const cactus = document.createElement('div');
    let cactusPosition = 2000;
    let randomTime = Math.random() * 6000;

    cactus.classList.add('cactus');
    cactus.style.left = 2000 + 'px';
    background.appendChild(cactus)

    let leftInterval = setInterval(() => {
        cactusPosition -= 10;
        cactus.style.left = cactusPosition + 'px';
        if (cactusPosition < -60) {
            clearInterval(leftInterval);
            background.removeChild(cactus);
            pontos++;
            Score.textContent = 'Pontos: ' + pontos;
        } else if (cactusPosition > 0 && cactusPosition < 60 && position < 60) {
            //Game Over
            clearInterval(leftInterval);
            document.body.innerHTML = '<h1 class="Game-Over">Game Over!</h1>'
        } else {
            cactusPosition -= 10;
            cactus.style.left = cactusPosition + 'px';
        }
    }, 20);

    setTimeout(createCactus, randomTime);

    var numbers = [1, 4, 9];
    var roots = numbers.map(Math.sqrt);
    console.log(roots)
// roots é [1, 2, 3], numbers ainda é [1, 4, 9]
}

createCactus();
document.addEventListener("keydown", handleKeyUp);