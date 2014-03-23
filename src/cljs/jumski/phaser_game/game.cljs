(ns jumski.phaser_game.game
  (:require [clojure.browser.repl]))

(defn log [thing] (.log js/console thing))

(defn yoflaki [] (js/alert "Yo flaki!"))

(defn create [game]
  (let [stage (aget game "stage")]
    (aset stage "backgroundColor" "#009999"))
  ;; //  Enable p2 physics
  ;; game.physics.startSystem(Phaser.Physics.Arcade);
  ;; // game.physics.startSystem(Phaser.Physics.P2JS);

  ;; game.world.setBounds(0, 0, 800, 800);

  ;; // game.stage.backgroundColor = '#124184';
  ;; game.stage.backgroundColor = '#000';
  ;; // background = game.add.tileSprite(0, 0, 1000, 900, "background");

  ;; bmd = game.add.bitmapData(800, 600);
  ;; bmd.context.fillStyle = '#ffffff';

  ;; var bg = game.add.sprite(0, 0, bmd);

  ;; // game.physics.p2.gravity.y = 3;
  ;; // game.physics.p2.defaultRestitution = 0.8;

  ;; sprite = game.add.sprite(32, 450, 'lander');
  ;; sprite.x = 400;
  ;; sprite.y = 400;
  ;; sprite.anchor.x = 64
  ;; sprite.anchor.y = 180
  ;; game.camera.follow(sprite, Phaser.Camera.FOLLOW_LOCKON);

  ;; window.s = sprite;
  ;; window.b = sprite.body;

  ;; game.physics.enable( [sprite], Phaser.Physics.ARCADE);
  ;; // game.physics.p2.enable(sprite);

  ;; // sprite.body.fixedRotation = true;

  ;; text = game.add.text(20, 20, 'click to the left / right of the ship', { fill: '#ffffff', font: '14pt Arial' });
  )

(def opts (clj->js {:preload (fn []) :create create :update (fn [])}))
(def height 800)
(def width 600)
(def parent-element "main")
(def mode js/Phaser.AUTO)
(def bg-color "#009999")

(def game (js/Phaser.Game. height width mode parent-element opts))
