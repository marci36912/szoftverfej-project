[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/XbZw8B6J)
# 1.3 feladat

Adott egy sakktabla, melyre egy huszart es egy kiralyt helyezunk az abran lathato modon. A feladat az, hogy valamelyik figuraval a C jelu mezore  lepjunk. Csak azzal a figuraval lehet lepni a sakklepeseknek megfeleloen, amelyik eppen utesben van a masik altal.

![game](assets/img.png)

Target: (6,7), King: (1,5), Knight: (2,5)
TwoPhaseMove[from=(2,5), to=(0,4)] Target: (6,7), King: (1,5), Knight: (0,4)
TwoPhaseMove[from=(0,4), to=(1,6)] Target: (6,7), King: (1,5), Knight: (1,6)
TwoPhaseMove[from=(1,5), to=(2,4)] Target: (6,7), King: (2,4), Knight: (1,6)
TwoPhaseMove[from=(2,4), to=(3,5)] Target: (6,7), King: (3,5), Knight: (1,6)
TwoPhaseMove[from=(1,6), to=(2,4)] Target: (6,7), King: (3,5), Knight: (2,4)
TwoPhaseMove[from=(3,5), to=(4,5)] Target: (6,7), King: (4,5), Knight: (2,4)
TwoPhaseMove[from=(2,4), to=(3,6)] Target: (6,7), King: (4,5), Knight: (3,6)
TwoPhaseMove[from=(3,6), to=(4,4)] Target: (6,7), King: (4,5), Knight: (4,4)
TwoPhaseMove[from=(4,5), to=(5,6)] Target: (6,7), King: (5,6), Knight: (4,4)
TwoPhaseMove[from=(4,4), to=(6,5)] Target: (6,7), King: (5,6), Knight: (6,5)
TwoPhaseMove[from=(6,5), to=(4,6)] Target: (6,7), King: (5,6), Knight: (4,6)
TwoPhaseMove[from=(5,6), to=(6,7)] Target: (6,7), King: (6,7), Knight: (4,6)
