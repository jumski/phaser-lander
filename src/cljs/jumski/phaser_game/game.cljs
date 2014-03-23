(ns jumski.phaser_game.game
  (:require [clojure.browser.repl]))

(defn log [thing] (.log js/console thing))

(defn yoflaki [] (js/alert "Yo flaki!"))

(defn create-lander [game]
  (let [add    (aget game "add")
        sprite (.sprite add 32 450 "lander")
        anchor (aget sprite "anchor")
        camera (aget game "camera")
        phys   (aget game "physics")
        p2     (aget phys "p2")]
    (aset sprite "x" 400)
    (aset sprite "y" 400)
    (aset anchor "x" 30)
    (aset anchor "y" 30)
    (.follow camera sprite js/Phaser.Camera.FOLLOW_LOCKON)
    (.enable p2 sprite)))

;; sprite = game.add.sprite(32, 450, 'lander');
;; sprite.x = 400;
;; sprite.y = 400;
;; sprite.anchor.x = 64
;; sprite.anchor.y = 180
;; game.camera.follow(sprite, Phaser.Camera.FOLLOW_LOCKON);

(defn create [game]
  (let [stage (aget game "stage")
        world (aget game "world")
        physics (aget game "physics")
        ]
    (do
      (aset stage "backgroundColor" "#009999")
      (.setBounds world 0 0 800 800)
      (.startSystem physics js/Phaser.Physics.P2JS)
      (let [p2 (aget physics "p2")
            grav (aget p2 "gravity")]
        (aset grav "y" 3))
      (create-lander game))))

  ;; window.s = sprite;
  ;; window.b = sprite.body;

  ;; game.physics.enable( [sprite], Phaser.Physics.ARCADE);
  ;; // game.physics.p2.enable(sprite);

  ;; // sprite.body.fixedRotation = true;

  ;; text = game.add.text(20, 20, 'click to the left / right of the ship', { fill: '#ffffff', font: '14pt Arial' });

(def opts (clj->js {:preload (fn []) :create create :update (fn [])}))
(def height 800)
(def width 600)
(def parent-element "main")
(def mode js/Phaser.AUTO)
(def bg-color "#009999")

(def game (js/Phaser.Game. height width mode parent-element opts))
