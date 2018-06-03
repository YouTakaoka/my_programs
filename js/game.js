var is_running = false;

function start(){
    document.getElementById('button_center').textContent = "Stop";
    is_running = true;

}


function character(c, pos_x, pos_y, visible){
    this.c = c;
    this.pos = {x: pos_x, y: pos_y};
    this.visible = visible;
    this.dir = "s";
    
    this.element = document.createElement('div');
    this.element.textContent = "A";
    this.element.style.position = "absolute";
    this.element.style.left = x;
    this.element.style.top = y;    



    this.changeDir = function(d){
        this.dir = d;
    }

    this.mainLoop = function(){
        switch(dir){
        case "l":
            char.style.left -= "1px";
            break;
        case "r":
            char.style.right += "1px";
            break;
        }
    }

    this.run(){
        document.getElementById('main_window').appendChild(this.element);
        setInterval(this.mainLoop, 10);
    }
}
