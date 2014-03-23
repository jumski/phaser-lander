(ns jumski.phaser_lander.game
  (:require [clojure.browser.repl]))

(def sprites (clj->js []))
(aset js/window "sprites" sprites)

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
    (.enable p2 sprite)
    (aset sprite "fixedRotation" true)
    (aset sprite "rotation" (rand-int 100))
    (.push sprites sprite)
    sprite))

(defn create [game]
  (let [stage (aget game "stage")
        world (aget game "world")
        physics (aget game "physics")
        add (aget game "add")]
    (do
      (aset stage "backgroundColor" "#009999")
      (.tileSprite add 0 0 1000 900 "background")
      (.setBounds world 0 0 800 800)
      (.startSystem physics js/Phaser.Physics.P2JS)
      (let [p2 (aget physics "p2")
            grav (aget p2 "gravity")]
        (aset grav "y" 3))
      (create-lander game))))

(defn preload [game]
  (let [load (aget game "load")]
    (.image load "lander" "lander.png")
    (.image load "background" "space01.png")))

(defn update [game]
  )

(def opts (clj->js {:preload preload :create create :update update}))
(def height 800)
(def width 600)
(def parent-element "main")
(def mode js/Phaser.AUTO)
(def bg-color "#009999")

(def game (js/Phaser.Game. height width mode parent-element opts))
