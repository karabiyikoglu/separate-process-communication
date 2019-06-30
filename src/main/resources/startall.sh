#!/bin/bash
java -cp separate-process-communication.jar com.karabiyikoglu.ismail.separateprocess.app.SeparateProcessAppServerMain &
java -cp separate-process-communication.jar com.karabiyikoglu.ismail.separateprocess.app.Player initiator &
java -cp separate-process-communication.jar com.karabiyikoglu.ismail.separateprocess.app.Player second