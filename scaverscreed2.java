// Prithviraj Khandekar
// January 15 2013
// To raise the profile of the University of Brampton among high-school students.
// To make economics and my University interesting to high-school students.

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class scaverscreed2 extends Applet implements MouseListener, Runnable
{
    // Following variables are all global

    AudioClip music;    // the variable name used for the audio clip
    String name;    // the variable name of the person in order to use at the profile screen
    String charactername; // the name of the character ( to use at battles )
    Thread s;   // thread used to animate the time

    int i = 0;     // seconds
    int i2 = 0;    // minutes
    int i3 = 0;     // hours
    int screen = 0; // Global variable to hold what screen is to be displayed
    int menuscreenchange = 0; //decides which screen to display according to where the player has got to
    int screenchange = 0;   // determines which screen to go to after the user has gone back to the menu

    int item1change = 0;    // checks to see if the item1 has already been picked up
    int item2change = 0;    // checks to see if the item2 has already been picked up
    int item3change = 0;    // checks to see if the item3 has already been picked up
    int item4change = 0;    // checks to see if the item4 has already been picked up

    int healthy = 0;    //checks to see if the HP UP item has been used

    int attack1change = 0; // checks to see if the attack1 has already been picked up
    int attack2change = 0;  // checks to see if the attack2 has already been picked up
    int attack3change = 0; // checks to see if the attack3 has already been picked up
    int attack4change = 0; // checks to see if the attack4 has already been picked up
    int attack5change = 0; // checks to see if the attack5 has already been picked up
    int attack6change = 0; // checks to see if the attack6 has already been picked up

    int labscreenchange = 0;    // checks to see if the player has picked his/her starting attack
    int housescreenchange = 0;  // checks to see if the player has been to the lab before opening up hidden items
    int item = 0;   // checks to see which item has been picked up
    int attack = 0; // checks to see which attack has been picked up
    int show = 0;     /// if show = 0 then the switch screen shows items... otherwise it shows attacks
    int text = 0;   // checks to see which text to display at the paint screen

    /*
    0 = TitleScreen
    1 = InstructionScreen
    2 = Menu
    3 = first playing screen (contains the map)
    4 = second playing screen (2nd map)
    5 = profile screen where it shows the stats of the players
    6 = main character
    7 = yuri battle screen
    8 = upstairs
    9 = rival house
    10 = Lab
    11 = Tells the player what the game is about (intro screen #1)
    12 = Intro Screen ("AHH that was a nice nap!") Starts the game
    13 = Go to Lab screen (tells the layer to go to the lab first)
    14 = choose attack screen
    15 = TREASURE HUNT SCREEN
    16 = Switching screen (for switching the items and attacks)
    17 = ready to battle (asks whether or not to continue to battle)
    18 = when an opponent dies
    19 = Dead Screen
    20 = Toad Battle
    21 = Dead Pool Battle
    22 = YOU WON (after beating deadpool)
    23 = Mission Accomplished Screen
    24 = Instructions 2
    25 = instructions 3
    */

    //Screen Images
    Image TitleScreen, InstructionScreen, MenuScreen1, PlayScreen1, PlayScreen2, ProfileScreen, Chara, YuriBattle, Upstairs, Rivalhouse, LabScreen, IntroScreen, IntroScreenUpstairs, GoToLab, chooseAttack, treasureHunt, switchingScreen, readyBattle, nextBattle, DeadScreen, toadBattle, deadpoolBattle, YOUWON, missionAccomplished, InstructionScreen2, InstructionScreen3;   //Number Images for Display

    //pallet town
    int map2x = 228;  // x coordinate used on the village map
    int map2y = 254; // y coordinate used on the village map
    int posx2 = 13;     // position x coordinate in tracker array used on the village map
    int posy2 = 15;     // position y coordinate in tracker array used on the village map

    //first player house
    int xx = 594;   // x coorindate used for the downstairs part of the player house
    int yy = 295;   // y coorindate used for the downstairs part of the player house
    int posx = 19;  // pos x coorindate in tracker array used for the downstairs part of the player house
    int posy = 8;   // pos y coorindate in tracker array used for the downstairs part of the player house

    //upstairs part of player house
    int upstairsx = 390;    // x coordinate used for the upstairs part of the player house
    int upstairsy = 315;    // y coordinare used for the upstairs part of the players house
    int upposx = 5;         // pos x coordinate in tracker array used for the upstairs part of the player house
    int upposy = 8;         // oos y coordinate in tracker array used for the upstairs part of the player house

    //rival's house
    int rivalx = 0;     // x coordinate used for the rival's house
    int rivaly = 0;     // y coordinate used for the rivals house
    int posx4 = 0;      // pos x coordinate in tracker array used for the rivals house
    int posy4 = 0;      // pos y coordinate in tracker array used for the rivals house

    //lab
    int labx = 480;     // x coordinate used for the lab
    int laby = 480;     // y coordinate used for the lab
    int labposx = 13;   // pos x coordinate in tracker array used for the lab
    int labposy = 25;   // pos y coordinate in tracker array used for the lab

    // tracker array used for the downstairs player house
    int tracker[] [] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    //tracker array used for the pallet village map
    int trackermap2[] [] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    //tracker used for the rivals house
    int trackerrivalhouse[] [] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    // tracker used for the upstairs part of the players house
    int trackerupstairs[] [] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5, 1, 1, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    // tracker used for the lab
    int trackerlab[] [] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    //xp is 0 at the starting of the game (prints out at profile screen)
    int xp = 0;                                           // amount of xp earned by the player

    int stepstaken, stepsshown, levelnow = 0;             // amount of steps taken by the player, the steps that are shown in the g.fill rect, the level of the player

    //the enemy names
    String enemynames[] = {"Yuri", "Toad", "Deadpool"};
    int enemyhealth[] = {115, 230, 460};

    //player health
    int playerhealth = 115;
    int playerhealthshown = playerhealth * 4;

    //Player attack upgrades and item upgrades stored in arrays
    String attacknames[] = new String [4];
    int attackvalue[] = new int [4];
    String itemnames[] = new String [4];

    //Yuri's attacks values
    int yuriattack[] = {5, 8, 10, 12};

    //Toad's attacks values
    int toadattack[] = {25, 45, 40, 10};

    //Deadpool's attacks values
    int deadpoolattack[] = {50, 45, 30, 50};

    //Yuri's attack choice (later used in battles)
    public void yurichoice ()
    {
	int n = (int) (Math.random () * 4);
	playerhealth -= yuriattack [n];
    }


    //Toad's attack choics (later used in battles)
    public void toadchoice ()
    {
	int n = (int) (Math.random () * 4);
	playerhealth -= toadattack [n];
    }


    // DeadPool's attack choice (later used in battles)
    public void deadpoolchoice ()
    {
	int n = (int) (Math.random () * 4);
	playerhealth -= deadpoolattack [n];
    }


    //resets the item Attack Higher's ability to 0 (later used in battles)
    public void resetattackhigher ()
    {
	if (attacknames [0].equals ("Arc Lightning"))
	    attackvalue [0] = 15;
	else if (attacknames [0].equals ("Shockwave Blast"))
	    attackvalue [0] = 20;
	else if (attacknames [0].equals ("Lightning Strike"))
	    attackvalue [0] = 10;
	else if (attacknames [0].equals ("Overload Burst"))
	    attackvalue [0] = 30;
	else if (attacknames [0].equals ("Shatter Blast"))
	    attackvalue [0] = 30;
	else if (attacknames [0].equals ("Alpha Bolt"))
	    attackvalue [0] = 20;
	else if (attacknames [0].equals ("Graviton Blast"))
	    attackvalue [0] = 25;
	else if (attacknames [0].equals ("Fire Storm"))
	    attackvalue [0] = 20;
	else if (attacknames [0].equals ("Punch"))
	    attackvalue [0] = 5;
	else if (attacknames [0].equals ("Ionic Storm"))
	    attackvalue [0] = 50;

	if (attacknames [1].equals ("Shatter Blast"))
	    attackvalue [1] = 30;
	else if (attacknames [1].equals ("Alpha Bolt"))
	    attackvalue [1] = 20;
	else if (attacknames [1].equals ("Graviton Blast"))
	    attackvalue [1] = 25;
	else if (attacknames [1].equals ("Fire Storm"))
	    attackvalue [1] = 20;
	else if (attacknames [1].equals ("Punch"))
	    attackvalue [1] = 5;
	else if (attacknames [1].equals ("Ionic Storm"))
	    attackvalue [1] = 50;

	if (attacknames [2].equals ("Shatter Blast"))
	    attackvalue [2] = 30;
	else if (attacknames [2].equals ("Alpha Bolt"))
	    attackvalue [2] = 20;
	else if (attacknames [2].equals ("Graviton Blast"))
	    attackvalue [2] = 25;
	else if (attacknames [2].equals ("Fire Storm"))
	    attackvalue [2] = 20;
	else if (attacknames [2].equals ("Punch"))
	    attackvalue [2] = 5;
	else if (attacknames [2].equals ("Ionic Storm"))
	    attackvalue [2] = 50;

	if (attacknames [3].equals ("Shatter Blast"))
	    attackvalue [3] = 30;
	else if (attacknames [3].equals ("Alpha Bolt"))
	    attackvalue [3] = 20;
	else if (attacknames [3].equals ("Graviton Blast"))
	    attackvalue [3] = 25;
	else if (attacknames [3].equals ("Fire Storm"))
	    attackvalue [3] = 20;
	else if (attacknames [3].equals ("Punch"))
	    attackvalue [3] = 5;
	else if (attacknames [3].equals ("Ionic Storm"))
	    attackvalue [3] = 50;

    }


    // resets all the variables used in the game to 0 and resets the game when called
    public void resetfullgame ()
    {
	screen = 0;
	menuscreenchange = 0;

	item1change = 0;
	item2change = 0;
	item3change = 0;
	item4change = 0;

	healthy = 0;

	attack1change = 0;
	attack2change = 0;
	attack3change = 0;
	attack4change = 0;
	attack5change = 0;
	attack6change = 0;

	labscreenchange = 0;
	housescreenchange = 0;

	screenchange = 0;
	item = 0;
	attack = 0;
	show = 0;
	text = 0;

	//pallet town
	map2x = 228;
	map2y = 254;
	posx2 = 13;
	posy2 = 15;

	//first player house
	xx = 594;
	yy = 295;
	posx = 19;
	posy = 8;

	//upstairs part of player house
	upstairsx = 390;
	upstairsy = 315;
	upposx = 5;
	upposy = 8;

	//rival's house
	rivalx = 0;
	rivaly = 0;
	posx4 = 0;
	posy4 = 0;

	//lab
	labx = 480;
	laby = 480;
	labposx = 13;
	labposy = 25;

	xp = 0;
	stepstaken = 0;
	levelnow = 0;

	//the enemy name
	enemyhealth [0] = 115;
	enemyhealth [1] = 230;
	enemyhealth [2] = 460;

	//player health
	playerhealth = 115;
	playerhealthshown = playerhealth * 4;

	//playerattacks
	attacknames [0] = "Empty";
	attacknames [1] = "Empty";
	attacknames [2] = "Empty";
	attacknames [3] = "Empty";
	attackvalue [0] = 0;
	attackvalue [1] = 0;
	attackvalue [2] = 0;
	attackvalue [3] = 0;
	itemnames [0] = "Empty";
	itemnames [1] = "Empty";
	itemnames [2] = "Empty";
	itemnames [3] = "Empty";

	//Yuri's attacks
	yuriattack [0] = 5;
	yuriattack [1] = 8;
	yuriattack [2] = 10;
	yuriattack [3] = 12;

	//Toad's attacks
	toadattack [0] = 25;
	toadattack [1] = 45;
	toadattack [2] = 40;
	toadattack [3] = 10;

	//Deadpool's attacks
	deadpoolattack [0] = 15;
	deadpoolattack [1] = 30;
	deadpoolattack [2] = 45;
	deadpoolattack [3] = 25;
    }


    public void init ()
    {
	resize (1000, 650);         // resizes the applet to desired size

	music = getAudioClip (getCodeBase (), "Black_Ops_2_Epic_Multiplayer_Background_Music.wav"); //
	music.loop ();

	s = new Thread (this);      // initializes a new thread
	s.start ();                 // starts running the thread

	i = 0;                      // the starting value for seconds (time in profile screen)
	i2 = 0;                     // the starting value for minutes (time in profile screen)
	i3 = 0;                     // the starting value for hours (time in profile screen)

	//make all the item's empty instead of null
	itemnames [0] = "Empty";
	itemnames [1] = "Empty";
	itemnames [2] = "Empty";
	itemnames [3] = "Empty";

	//make all the attacks empty instead of null
	attacknames [0] = "Empty";
	attacknames [1] = "Empty";
	attacknames [2] = "Empty";
	attacknames [3] = "Empty";

	name = JOptionPane.showInputDialog ("What is your first name?");   //asks the player's name before running the program

	// if the name is null then make sure it doesn't say null on screen
	if (name == null)
	    name = "Raj";

	//asks which character the player wants to be (does not affect game play)
	String[] possibleValues = {"Cole", "Soap", "Jak", "Dax"};
	charactername = (String) JOptionPane.showInputDialog (null,
		"Choose Your Character Name", "Input", JOptionPane.INFORMATION_MESSAGE, null,
		possibleValues, possibleValues [0]);

	//makes sure the applet does not print null
	if (charactername == null)
	    charactername = "Cole";

	screen = 0; // start on screen 0, the Title Screen
	addMouseListener (this); // add the mouselistener to the applet

	// Get the TitleScreen Picture
	TitleScreen = getImage (getDocumentBase (), "title.jpg"); // self-made, no resources used

	// Get the InstructionScreen Picture
	InstructionScreen = getImage (getDocumentBase (), "instructions1.jpg"); // http://gameswallz.com/wp-content/uploads/2010/08/AssassinscreedGamesWallpaper37.jpg

	//Get the InstructionScreen2 Picture
	InstructionScreen2 = getImage (getDocumentBase (), "instructions2.jpg"); // http://gameswallz.com/wp-content/uploads/2010/08/AssassinscreedGamesWallpaper37.jpg

	//Get the InstructionScreen3 Picture
	InstructionScreen3 = getImage (getDocumentBase (), "instructions3.jpg"); // http://gameswallz.com/wp-content/uploads/2010/08/AssassinscreedGamesWallpaper37.jpg

	// Get the MenuScreen Picture
	MenuScreen1 = getImage (getDocumentBase (), "menu.jpg"); // http://www.gamekyo.com/images_1/c76a52b94f49bc005258e1430023a7fc20090527020233.png

	// Get the PlayScreen Picture
	PlayScreen1 = getImage (getDocumentBase (), "maporiginal.jpg"); // http://spritedatabase.net/files/gba/1099/Background/PalletBuildings.gif

	PlayScreen2 = getImage (getDocumentBase (), "map2original.jpg"); // http://www.spotlighttv.nl/spotlight/pokemon-stop-motion-avontuur/

	// Get the ProfileScreen Picture
	ProfileScreen = getImage (getDocumentBase (), "profile.jpg");  // http://1.bp.blogspot.com/_I7vXRzy5sq4/S8TAZQjFx5I/AAAAAAAAAJU/U_sqMSS5kj4/s1600/dmfull+(71).jpg
	// http://archive.gg.ca/heraldry/pub-reg/ProjectPics/v362_UB209_20050085_badge.jpg

	// Get the image for the default character picture
	Chara = getImage (getDocumentBase (), "characterwalkright.gif"); // all character sprites are this site ----> http://media.beta.photobucket.com/user/slapshot4151/media/Player.png.html?filters[term]=rpg%20maker%20vx%208%20directional%20sprites&filters[primary]=images

	// Get the image for the Yuri Battle Screen
	YuriBattle = getImage (getDocumentBase (), "battlescreen1.jpg"); // http://www.vnpet.com/images/games/plain2.jpg
	// http://demortalz.com/wp-content/uploads/2012/01/pattern_texture_wallpaper_10.jpg

	// Get the image for the upstairs screen
	Upstairs = getImage (getDocumentBase (), "upstairsoriginal.jpg"); // http://spritedatabase.net/files/gba/1099/Background/PalletBuildings.gif

	// Get the image for the rivals house
	Rivalhouse = getImage (getDocumentBase (), "rivalhouse.jpg"); // http://spritedatabase.net/files/gba/1099/Background/PalletBuildings.gif

	// Get the image for the lab screen
	LabScreen = getImage (getDocumentBase (), "lab.jpg"); // http://spritedatabase.net/files/gba/1099/Background/PalletBuildings.gif

	// Get the image for the IntroScreen
	IntroScreen = getImage (getDocumentBase (), "introscreen.jpg"); // self-made

	// Get the image for the Intro Screen where it says he had a nice nap
	IntroScreenUpstairs = getImage (getDocumentBase (), "napupstairs.jpg"); // http://spritedatabase.net/files/gba/1099/Background/PalletBuildings.gif

	// Get the image for the GOTOLAB screen where it tells the player to go to the lab
	GoToLab = getImage (getDocumentBase (), "gotolab.jpg"); // self-made

	// Get the image chooseAttack when the player goes to the attack table in the lab
	chooseAttack = getImage (getDocumentBase (), "chooseattack.jpg"); // http://teknomode.com/wp-content/uploads/2012/07/Black_and_Blue_tech_background_by_Salwiak.jpg

	treasureHunt = getImage (getDocumentBase (), "lab2.jpg"); // http://spritedatabase.net/files/gba/1099/Background/PalletBuildings.gif

	switchingScreen = getImage (getDocumentBase (), "switchingscreen.jpg"); // http://teknomode.com/wp-content/uploads/2012/07/Black_and_Blue_tech_background_by_Salwiak.jpg

	readyBattle = getImage (getDocumentBase (), "readytobattle.jpg"); // http://sfiles.d1g.com/photos/37/94/3919437_max.jpg

	DeadScreen = getImage (getDocumentBase (), "gameover.jpg"); //

	nextBattle = getImage (getDocumentBase (), "nextbattle.jpg"); // http://sfiles.d1g.com/photos/37/94/3919437_max.jpg

	toadBattle = getImage (getDocumentBase (), "toadbattle.jpg"); // http://www.vnpet.com/images/games/plain2.jpg
	// http://demortalz.com/wp-content/uploads/2012/01/pattern_texture_wallpaper_10.jpg

	deadpoolBattle = getImage (getDocumentBase (), "deadpoolbattle.jpg"); // http://www.vnpet.com/images/games/plain2.jpg
	// http://demortalz.com/wp-content/uploads/2012/01/pattern_texture_wallpaper_10.jpg

	missionAccomplished = getImage (getDocumentBase (), "missionaccomplished.jpg"); // http://oyster.ignimgs.com/ve3d/images/07/90/79049_screen_and_depth_03.jpg

	Graphics g = getGraphics ();

	// call all the images
	InitImages ();
    }


    // used to animate the threads in profile screen
    public void run ()
    {
	while (true)            // only of the Thread is true it runs
	{
	    if (i == 60)        // if value of the seconds is 60, return the seconds back to 0
	    {
		i2++;           // increase the minutes by 1 everytime the seconds hits 60
		i = 0;          // puts the value of seconds back to 0
	    }
	    i++;                // increases the number of seconds each time
	    repaint ();

	    try
	    {
		s.sleep (1000); // 1000 milliseconds = 1 second, thus, the seconds are exact
	    }
	    catch (InterruptedException e)
	    {
		;
	    }
	}
    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    // when the mouse is released
    public void mouseReleased (MouseEvent fe)
    {
	// get the x and y co-ordinates of the mouse
	int x = fe.getX ();
	int y = fe.getY ();

	// TITLE SCREEN
	if (screen == 0)
	{
	    if (x > 148 && x < 359 && y > 575 && y < 618) // Play button
	    {
		TitleScreen = getImage (getDocumentBase (), "title.jpg");
		screen = 2;
	    }
	    else if (x > 668 && x < 883 && y > 578 && y < 620) // Instruction button
	    {
		TitleScreen = getImage (getDocumentBase (), "title.jpg");
		screen = 1;
	    }

	}
	// Instructions SCREEN 1
	else if (screen == 1)
	{
	    if (x > 14 && x < 270 && y > 578 && y < 638) // back button
	    {
		screen = 0;
	    }
	    else if (x > 728 && x < 984 && y > 577 && y < 635) // forward button
	    {
		screen = 24;
	    }
	}

	// Instructions Screen 2
	else if (screen == 24)
	{
	    if (x > 14 && x < 270 && y > 578 && y < 638) // back button
	    {
		screen = 1;
	    }
	    else if (x > 728 && x < 984 && y > 577 && y < 635) // forward button
	    {
		screen = 25;
	    }
	}

	//Instructions Screen 3
	else if (screen == 25)
	{
	    if (x > 14 && x < 270 && y > 578 && y < 638) // back button
	    {
		screen = 24;
	    }
	}

	// Menu Screen
	else if (screen == 2)
	{
	    MenuScreen1 = getImage (getDocumentBase (), "menu.jpg");

	    if (x > 44 && x < 258 && y > 70 && y < 133) // go to the campaign ( start the game )
	    {
		if (menuscreenchange == 0) // determines whether or not to display the intro screen
		    screen = 11;
		else if (menuscreenchange == 1) // determines which screen to display
		{
		    if (screenchange == 1)
			screen = 3;
		    else if (screenchange == 2)
			screen = 4;
		    else if (screenchange == 3)
			screen = 8;
		    else if (screenchange == 4)
			screen = 9;
		    else if (screenchange == 5)
			screen = 10;
		}
	    }
	    else if (x > 40 && x < 258 && y > 270 && y < 334) // go to the profile screen
	    {
		screen = 5;
	    }
	    else if (x > 40 && x < 258 && y > 471 && y < 531) // go to the title screen
	    {
		screen = 0;

	    }
	}

	// Player's downstairs house
	else if (screen == 3)
	{
	    screenchange = 1; // tells the campaign to display screen 3 when campaign is clicked

	    if (x > 850 && x < 1000 && y > 613 && y < 650) // go back to menu
	    {
		screen = 2;
	    }

	    if (x > 906 && x < 945 && y > 371 && y < 418) // move up
	    {
		coordinates ();
		Chara = getImage (getDocumentBase (), "characterwalkup.gif");

		if (check (posx, posy - 1)) // checks to see if the next position is blocked
		{
		    yy -= 15;
		    posy--;
		    stepstaken++;
		}
	    }
	    else if (x > 905 && x < 944 && y > 461 && y < 508) // move down
	    {
		coordinates ();
		Chara = getImage (getDocumentBase (), "characterwalkdown.gif");

		if (check (posx, posy + 1))  // checks to see if the next position is blocked
		{
		    yy += 15;
		    posy++;
		    stepstaken++;
		}
	    }
	    else if (x > 946 && x < 994 && y > 419 && y < 459) // move right
	    {
		coordinates ();
		Chara = getImage (getDocumentBase (), "characterwalkright.gif");

		if (check (posx + 1, posy))  // checks to see if the next position is blocked
		{
		    xx += 15;
		    posx++;
		    stepstaken++;
		}
	    }
	    else if (x > 856 && x < 904 && y > 419 && y < 459) // move left
	    {
		coordinates ();
		Chara = getImage (getDocumentBase (), "characterwalkleft.gif");

		if (check (posx - 1, posy))   // checks to see if the next position is blocked
		{
		    xx -= 15;
		    posx--;
		    stepstaken++;
		}
	    }
	}

	// The Main VILLAGE Map
	else if (screen == 4)
	{
	    screenchange = 2; // tells the campaign button to display screen 4 when clicked

	    if (x > 850 && x < 1000 && y > 613 && y < 650) // go back to menu
	    {
		screen = 2;
	    }

	    if (x > 906 && x < 945 && y > 371 && y < 418) // move up
	    {
		coordinatesmap2 ();
		Chara = getImage (getDocumentBase (), "characterwalkup.gif");

		if (checkmap2 (posx2, posy2 - 1))  // checks to see if the next position is blocked
		{
		    map2y -= 15;
		    stepstaken++;
		    posy2--;
		}
	    }
	    else if (x > 905 && x < 944 && y > 461 && y < 508) // move down
	    {
		coordinatesmap2 ();
		Chara = getImage (getDocumentBase (), "characterwalkdown.gif");
		if (checkmap2 (posx2, posy2 + 1))  // checks to see if the next position is blocked
		{
		    map2y += 15;
		    stepstaken++;
		    posy2++;
		}
	    }
	    else if (x > 946 && x < 994 && y > 419 && y < 459) // move right
	    {
		coordinatesmap2 ();
		Chara = getImage (getDocumentBase (), "characterwalkright.gif");
		if (checkmap2 (posx2 + 1, posy2))  // checks to see if the next position is blocked
		{
		    map2x += 15;
		    stepstaken++;
		    posx2++;
		}
	    }
	    else if (x > 856 && x < 904 && y > 419 && y < 459) // move left
	    {
		coordinatesmap2 ();
		Chara = getImage (getDocumentBase (), "characterwalkleft.gif");
		if (checkmap2 (posx2 - 1, posy2))  // checks to see if the next position is blocked
		{
		    map2x -= 15;
		    stepstaken++;
		    posx2--;
		}
	    }
	}
	// upstairs screen
	else if (screen == 8)
	{
	    screenchange = 3; // tells the campaign button to display screen 8 when clicked

	    if (x > 850 && x < 1000 && y > 613 && y < 650) // go back to menu
	    {
		screen = 2;
	    }

	    if (x > 906 && x < 945 && y > 371 && y < 418) // move up
	    {
		coordinatesupstairs ();
		Chara = getImage (getDocumentBase (), "characterwalkup.gif");
		if (checkupstairs (upposx, upposy - 1))  // checks to see if the next position is blocked
		{
		    upstairsy -= 15;
		    stepstaken++;
		    upposy--;
		}
	    }
	    else if (x > 905 && x < 944 && y > 461 && y < 508) // move down
	    {
		coordinatesupstairs ();
		Chara = getImage (getDocumentBase (), "characterwalkdown.gif");
		if (checkupstairs (upposx, upposy + 1))  // checks to see if the next position is blocked
		{
		    upstairsy += 15;
		    stepstaken++;
		    upposy++;
		}
	    }
	    else if (x > 946 && x < 994 && y > 419 && y < 459) // move right
	    {
		coordinatesupstairs ();
		Chara = getImage (getDocumentBase (), "characterwalkright.gif");
		if (checkupstairs (upposx + 1, upposy))  // checks to see if the next position is blocked
		{
		    upstairsx += 15;
		    stepstaken++;
		    upposx++;
		}
	    }
	    else if (x > 856 && x < 904 && y > 419 && y < 459) // move left
	    {
		coordinatesupstairs ();
		Chara = getImage (getDocumentBase (), "characterwalkleft.gif");
		if (checkupstairs (upposx - 1, upposy))  // checks to see if the next position is blocked
		{
		    upstairsx -= 15;
		    stepstaken++;
		    upposx--;
		}
	    }
	}
	// rival's house
	else if (screen == 9)
	{
	    screenchange = 4; // tells the campaign button to display screen 9 when clicked

	    if (x > 850 && x < 1000 && y > 613 && y < 650) // go back to menu
	    {
		screen = 2;
	    }

	    if (x > 906 && x < 945 && y > 371 && y < 418) // move up
	    {
		coordinatesrival ();
		Chara = getImage (getDocumentBase (), "characterwalkup.gif");
		if (checkrivalmap (posx4, posy4 - 1))  // checks to see if the next position is blocked
		{
		    rivaly -= 15;
		    stepstaken++;
		    posy4--;
		}
	    }
	    else if (x > 905 && x < 944 && y > 461 && y < 508) // move down
	    {
		coordinatesrival ();
		Chara = getImage (getDocumentBase (), "characterwalkdown.gif");
		if (checkrivalmap (posx4, posy4 + 1))  // checks to see if the next position is blocked
		{
		    rivaly += 15;
		    stepstaken++;
		    posy4++;
		}
	    }
	    else if (x > 946 && x < 994 && y > 419 && y < 459) // move right
	    {
		coordinatesrival ();
		Chara = getImage (getDocumentBase (), "characterwalkright.gif");
		if (checkrivalmap (posx4 + 1, posy4))  // checks to see if the next position is blocked
		{
		    rivalx += 15;
		    stepstaken++;
		    posx4++;
		}
	    }
	    else if (x > 856 && x < 904 && y > 419 && y < 459) // move left
	    {
		coordinatesrival ();
		Chara = getImage (getDocumentBase (), "characterwalkleft.gif");
		if (checkrivalmap (posx4 - 1, posy4))  // checks to see if the next position is blocked
		{
		    rivalx -= 15;
		    stepstaken++;
		    posx4--;
		}
	    }
	}

	else if (screen == 10) // For the Lab Screen
	{

	    screenchange = 5; // tells the campaign button to display screen 10 when clicked

	    if (x > 850 && x < 1000 && y > 613 && y < 650) // go back to menu
	    {
		screen = 2;
	    }

	    if (x > 906 && x < 945 && y > 371 && y < 418) // move up
	    {
		coordinateslab ();
		Chara = getImage (getDocumentBase (), "characterwalkup.gif");
		if (checklab (labposx, labposy - 1))  // checks to see if the next position is blocked
		{
		    laby -= 15;
		    stepstaken++;
		    labposy--;
		}
	    }
	    else if (x > 905 && x < 944 && y > 461 && y < 508) // move down
	    {
		coordinateslab ();
		Chara = getImage (getDocumentBase (), "characterwalkdown.gif");
		if (checklab (labposx, labposy + 1))  // checks to see if the next position is blocked
		{
		    laby += 15;
		    stepstaken++;
		    labposy++;
		}
	    }
	    else if (x > 946 && x < 994 && y > 419 && y < 459) // move right
	    {
		coordinateslab ();
		Chara = getImage (getDocumentBase (), "characterwalkright.gif");
		if (checklab (labposx + 1, labposy))  // checks to see if the next position is blocked
		{
		    labx += 15;
		    stepstaken++;
		    labposx++;
		}

	    }
	    else if (x > 856 && x < 904 && y > 419 && y < 459) // move left
	    {
		coordinateslab ();
		Chara = getImage (getDocumentBase (), "characterwalkleft.gif");
		if (checklab (labposx - 1, labposy))  // checks to see if the next position is blocked
		{
		    labx -= 15;
		    stepstaken++;
		    labposx--;
		}
	    }
	}

	else if (screen == 5)   // the PROFILE SCREEN
	{
	    screen = 2; // the menu screen
	}

	else if (screen == 11)  // the INTRO SCREEN
	{
	    if (x > 820 && x < 987 && y > 577 && y < 638) // next button
	    {
		screen = 12;
		IntroScreen = getImage (getDocumentBase (), "introscreen.jpg");
	    }
	    else if (x > 23 && x < 191 && y > 579 && y < 639) // back button
	    {
		screen = 2;
		IntroScreen = getImage (getDocumentBase (), "introscreen.jpg");
	    }
	}

	else if (screen == 12) // the UPSTAIRS INTRO SCREEN
	{
	    screen = 8;
	    menuscreenchange = 1;
	}

	else if (screen == 13) // the GO TO LAB SCREEN
	{
	    screen = 4;
	    map2y += 15;
	    posy2++;
	}

	else if (screen == 14) // for the PICKING ATTACK SCREEN
	{
	    if (x > 22 && x < 454 && y > 207 && y < 276) // ARC Lightning pick (stores in slot 1)
	    {
		attacknames [0] = ("Arc Lightning");
		attackvalue [0] = 15;

		labscreenchange = 1;
		housescreenchange = 1;

		screen = 15;
	    }
	    else if (x > 507 && x < 981 && y > 199 && y < 274) // Shockwave blast (stores in slot 1)
	    {
		attacknames [0] = ("Shockwave Blast");
		attackvalue [0] = 20;

		labscreenchange = 1;
		housescreenchange = 1;

		screen = 15;
	    }
	    else if (x > 23 && x < 511 && y > 456 && y < 527) // Lightning Strike (stores in slot 1)
	    {
		attacknames [0] = ("Lightning Strike");
		attackvalue [0] = 10;

		labscreenchange = 1;
		housescreenchange = 1;

		screen = 15;
	    }
	    else if (x > 551 && x < 984 && y > 453 && y < 526) // Overload Burst (stores in slot 1)
	    {
		attacknames [0] = ("Overload Burst");
		attackvalue [0] = 30;

		labscreenchange = 1;
		housescreenchange = 1;

		screen = 15;
	    }
	}

	// tells the player that there are items hidden all over the village
	else if (screen == 15)
	{
	    screen = 10;
	}

	else if (screen == 16) // item swithching screen
	{
	    if (x > 18 && x < 250 && y > 542 && y < 597) // drops the item and returns back to appropriate screen
	    {
		if (item == 1 || attack == 1)
		{
		    screen = 8;
		    upstairsy -= 15;
		    upposy--;
		}
		else if (item == 2)
		{
		    screen = 8;
		    upstairsy += 15;
		    upposy++;
		}
		else if (item == 3 || attack == 2 || attack == 3)
		{
		    screen = 3;
		    yy += 15;
		    posy++;
		}
		else if (item == 4 || attack == 6)
		{
		    screen = 4;
		    map2y -= 15;
		    posy2--;
		}
		else if (attack == 4 || attack == 5)
		{
		    screen = 4;
		    map2y += 30;
		    posy2 += 2;
		}
	    }

	    else if (show == 0) //will replace the items, will store in itemnames []
	    {
		if (x > 129 && x < 432 && y > 309 && y < 374) // the left top box, stores in itemnames [0]
		{
		    xp += 200;
		    if (item == 1)
		    {
			itemnames [0] = ("First Aid Kit");
			screen = 8;
			item1change = 1;
		    }
		    else if (item == 2)
		    {
			itemnames [0] = ("High Attacker");
			screen = 8;
			item2change = 1;
		    }
		    else if (item == 3)
		    {
			itemnames [0] = ("First Aid Kit");
			screen = 3;
			item3change = 1;
		    }
		    else if (item == 4)
		    {
			itemnames [0] = ("HP UP");
			screen = 4;
			item4change = 1;
		    }
		}
		else if (x > 570 && x < 918 && y > 308 && y < 373) // the right top box slot, stores in itemnames [1]
		{
		    xp += 200;
		    if (item == 1)
		    {
			itemnames [1] = ("First Aid Kit");
			screen = 8;
			item1change = 1;
		    }
		    else if (item == 2)
		    {
			itemnames [1] = ("High Attacker");
			screen = 8;
			item2change = 1;
		    }
		    else if (item == 3)
		    {
			itemnames [1] = ("First Aid Kit");
			screen = 3;
			item3change = 1;
		    }
		    else if (item == 4)
		    {
			itemnames [1] = ("HP UP");
			screen = 4;
			item4change = 1;
		    }
		}
		else if (x > 129 && x < 432 && y > 417 && y < 481) // the bottom left box, stores in itemnames [2]
		{
		    xp += 200;
		    if (item == 1)
		    {
			itemnames [2] = ("First Aid Kit");
			screen = 8;
			item1change = 1;
		    }
		    else if (item == 2)
		    {
			itemnames [2] = ("High Attacker");
			screen = 8;
			item2change = 1;
		    }
		    else if (item == 3)
		    {
			itemnames [2] = ("First Aid Kit");
			screen = 3;
			item3change = 1;
		    }
		    else if (item == 4)
		    {
			itemnames [2] = ("HP UP");
			screen = 4;
			item4change = 1;
		    }
		}
		else if (x > 571 && x < 918 && y > 417 && y < 480) // the bottom right box, stores in itemnames [3]
		{
		    xp += 200;
		    if (item == 1)
		    {
			itemnames [3] = ("First Aid Kit");
			screen = 8;
			item1change = 1;
		    }
		    else if (item == 2)
		    {
			itemnames [3] = ("High Attacker");
			screen = 8;
			item2change = 1;
		    }
		    else if (item == 3)
		    {
			itemnames [3] = ("First Aid Kit");
			screen = 3;
			item3change = 1;
		    }
		    else if (item == 4)
		    {
			itemnames [3] = ("HP UP");
			screen = 4;
			item4change = 1;
		    }
		}
	    }
	    else if (show == 1) // will replace the attacks, will store in attacknames []
	    {

		if (x > 129 && x < 432 && y > 309 && y < 374) // the left top box, stores in attackvalue [0]
		{
		    xp += 200;
		    if (attack == 1)
		    {
			attacknames [0] = ("Shatter Blast");
			attackvalue [0] = 30;
			screen = 8;
			attack1change = 1;
		    }
		    else if (attack == 2)
		    {
			attacknames [0] = ("Alpha Bolt");
			attackvalue [0] = 20;
			screen = 3;
			attack2change = 1;
		    }
		    else if (attack == 3)
		    {
			attacknames [0] = ("Graviton Blast");
			attackvalue [0] = 25;
			screen = 3;
			attack3change = 1;
		    }
		    else if (attack == 4)
		    {
			attacknames [0] = ("Fire Storm");
			attackvalue [0] = 20;
			screen = 4;
			attack4change = 1;
		    }
		    else if (attack == 5)
		    {
			attacknames [0] = ("Punch");
			attackvalue [0] = 5;
			screen = 4;
			attack5change = 1;
		    }
		    else if (attack == 6)
		    {
			attacknames [0] = ("Ionic Storm");
			attackvalue [0] = 50;
			screen = 4;
			attack6change = 1;
		    }
		}
		else if (x > 570 && x < 918 && y > 308 && y < 373) // the right top box, stores in attackvalue [1]
		{
		    xp += 200;
		    if (attack == 1)
		    {
			attacknames [1] = ("Shatter Blast");
			attackvalue [1] = 30;
			screen = 8;
			attack1change = 1;
		    }
		    else if (attack == 2)
		    {
			attacknames [1] = ("Alpha Bolt");
			attackvalue [1] = 20;
			screen = 3;
			attack2change = 1;
		    }
		    else if (attack == 3)
		    {
			attacknames [1] = ("Graviton Blast");
			attackvalue [1] = 25;
			screen = 3;
			attack3change = 1;
		    }
		    else if (attack == 4)
		    {
			attacknames [1] = ("Fire Storm");
			attackvalue [1] = 20;
			screen = 4;
			attack4change = 1;
		    }
		    else if (attack == 5)
		    {
			attacknames [1] = ("Punch");
			attackvalue [1] = 5;
			screen = 4;
			attack5change = 1;
		    }
		    else if (attack == 6)
		    {
			attacknames [1] = ("Ionic Storm");
			attackvalue [1] = 50;
			screen = 4;
			attack6change = 1;
		    }
		}
		else if (x > 129 && x < 432 && y > 417 && y < 481) // the bottom left box, stores in attackvalue [2]
		{
		    xp += 200;
		    if (attack == 1)
		    {
			attacknames [2] = ("Shatter Blast");
			attackvalue [2] = 30;
			screen = 8;
			attack1change = 1;
		    }
		    else if (attack == 2)
		    {
			attacknames [2] = ("Alpha Bolt");
			attackvalue [2] = 20;
			screen = 3;
			attack2change = 1;
		    }
		    else if (attack == 3)
		    {
			attacknames [2] = ("Graviton Blast");
			attackvalue [2] = 25;
			screen = 3;
			attack3change = 1;
		    }
		    else if (attack == 4)
		    {
			attacknames [2] = ("Fire Storm");
			attackvalue [2] = 20;
			screen = 4;
			attack4change = 1;
		    }
		    else if (attack == 5)
		    {
			attacknames [2] = ("Punch");
			attackvalue [2] = 5;
			screen = 4;
			attack5change = 1;
		    }
		    else if (attack == 6)
		    {
			attacknames [2] = ("Ionic Storm");
			attackvalue [2] = 50;
			screen = 4;
			attack6change = 1;
		    }
		}
		else if (x > 571 && x < 918 && y > 417 && y < 480) // the bottom right box, stores in attackvalue [2]
		{
		    xp += 200;
		    if (attack == 1)
		    {
			attacknames [3] = ("Shatter Blast");
			attackvalue [3] = 30;
			screen = 8;
			attack1change = 1;
		    }
		    else if (attack == 2)
		    {
			attacknames [3] = ("Alpha Bolt");
			attackvalue [3] = 20;
			screen = 3;
			attack2change = 1;
		    }
		    else if (attack == 3)
		    {
			attacknames [3] = ("Graviton Blast");
			attackvalue [3] = 25;
			screen = 3;
			attack3change = 1;
		    }
		    else if (attack == 4)
		    {
			attacknames [3] = ("Fire Storm");
			attackvalue [3] = 20;
			screen = 4;
			attack4change = 1;
		    }
		    else if (attack == 5)
		    {
			attacknames [3] = ("Punch");
			attackvalue [3] = 5;
			screen = 4;
			attack5change = 1;
		    }
		    else if (attack == 6)
		    {
			attacknames [3] = ("Ionic Storm");
			attackvalue [3] = 50;
			screen = 4;
			attack6change = 1;
		    }
		}
	    }
	}

	// asks if the player is ready for battle
	else if (screen == 17)
	{
	    if (x > 0 && x < 286 && y > 369 && y < 650) // Yes he is ready button
	    {
		screen = 7;
	    }
	    else if (x > 715 && x < 1000 && y > 369 && y < 650) // No, send him back to previous map
	    {
		screen = 4;
		map2y += 15;
		posy2++;
	    }
	}

	//when the opponent dies
	else if (screen == 18)
	{
	    if (x > 0 && x < 286 && y > 369 && y < 650) // when Yes is clicked
	    {
		if (text == 2) // display YOU WON,
		{
		    screen = 20;
		    resetattackhigher ();
		}
		else if (text == 3) // display YOU WON
		{
		    screen = 21;
		    resetattackhigher ();
		}
	    }
	}

	else if (screen == 7) // YURI BATTLE SCREEN
	{
	    if (playerhealth <= 0) // when the player dies
	    {
		screen = 19;
	    }
	    else if (enemyhealth [0] <= 0) // when the enemy dies
	    {
		text = 2;
		screen = 18;
		playerhealth = 115;
		healthy = 0;
	    }

	    else if (playerhealth > 0 && enemyhealth [0] > 0) // if both characters are alive
	    {
		text = 1;

		if (x > 30 && x < 260 && y > 477 && y < 538) // attack 1
		{
		    if (attackvalue [0] > 0)
		    {
			enemyhealth [0] -= attackvalue [0];
			yurichoice ();
		    }
		}
		else if (x > 30 && x < 260 && y > 560 && y < 630) // attack 2
		{
		    if (attackvalue [2] > 0)
		    {
			enemyhealth [0] -= attackvalue [2];
			yurichoice ();
		    }
		}
		else if (x > 280 && x < 473 && y > 477 && y < 538) // attack 3
		{
		    if (attackvalue [1] > 0)
		    {
			enemyhealth [0] -= attackvalue [1];
			yurichoice ();
		    }
		}
		else if (x > 280 && x < 473 && y > 560 && y < 630) // attack 4
		{
		    if (attackvalue [3] > 0)
		    {
			enemyhealth [0] -= attackvalue [3];
			yurichoice ();
		    }
		}

		else if (x > 533 && x < 750 && y > 478 && y < 540)   // first item, uses the item and then empties it
		{
		    if (!itemnames [0].equals ("Empty"))
		    {
			if (itemnames [0].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [0].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [0].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			}
			itemnames [0] = "Empty";
			yurichoice ();
		    }
		}

		else if (x > 770 && x < 970 && y > 478 && y < 540) // second item, uses the item and then empties it
		{
		    if (!itemnames [1].equals ("Empty"))
		    {
			if (itemnames [1].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [1].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;

			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;

			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;

			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [1].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			}
			itemnames [1] = ("Empty");
			yurichoice ();
		    }
		}


		else if (x > 533 && x < 750 && y > 562 && y < 633) // third item, uses the item and then empties it
		{
		    if (!itemnames [2].equals ("Empty"))
		    {
			if (itemnames [2].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [2].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [2].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [2] = "Empty";
			yurichoice ();
		    }
		}

		else if (x > 770 && x < 970 && y > 562 && y < 633) // fourth item, uses the item and then empties it
		{
		    if (!itemnames [3].equals ("Empty"))
		    {
			if (itemnames [3].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [3].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [3].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [3] = "Empty";
			yurichoice ();
		    }
		}
	    }
	}

	//when the player dies
	else if (screen == 19)
	{
	    if (x > 194 && x < 404 && y > 301 && y < 349) // play again button is clicked
	    {
		resetfullgame ();
	    }
	}


	else if (screen == 20) //TOAD BATTLE SCREEN
	{
	    if (playerhealth <= 0) // when the player dies
	    {
		screen = 19;
	    }
	    else if (enemyhealth [1] <= 0) // when the opponent dies
	    {
		text = 3;
		screen = 18;
		playerhealth = 115;
		healthy = 0;
	    }

	    else if (playerhealth > 0 && enemyhealth [1] > 0) // when both characters are alive
	    {
		text = 1;

		if (x > 30 && x < 260 && y > 477 && y < 538) // attack 1
		{
		    if (attackvalue [0] > 0)
		    {
			enemyhealth [1] -= attackvalue [0];
			toadchoice ();
		    }
		}
		else if (x > 30 && x < 260 && y > 560 && y < 630) // attack 2
		{
		    if (attackvalue [2] > 0)
		    {
			enemyhealth [1] -= attackvalue [2];
			toadchoice ();
		    }
		}
		else if (x > 280 && x < 473 && y > 477 && y < 538) // attack 3
		{
		    if (attackvalue [1] > 0)
		    {
			enemyhealth [1] -= attackvalue [1];
			toadchoice ();
		    }
		}
		else if (x > 280 && x < 473 && y > 560 && y < 630) // attack 4
		{
		    if (attackvalue [3] > 0)
		    {
			enemyhealth [1] -= attackvalue [3];
			toadchoice ();
		    }
		}

		else if (x > 533 && x < 750 && y > 478 && y < 540)   // first item, uses the item and then empties it
		{
		    if (!itemnames [0].equals ("Empty"))
		    {
			if (itemnames [0].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [0].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [0].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			}
			itemnames [0] = "Empty";
			toadchoice ();
		    }
		}

		else if (x > 770 && x < 970 && y > 478 && y < 540) // second item, uses the item and then empties it
		{
		    if (!itemnames [1].equals ("Empty"))
		    {
			if (itemnames [1].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [1].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;

			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;

			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;

			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [1].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [1] = ("Empty");
			toadchoice ();
		    }
		}


		else if (x > 533 && x < 750 && y > 562 && y < 633) // third item, uses the item and then empties it
		{
		    if (!itemnames [2].equals ("Empty"))
		    {
			if (itemnames [2].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [2].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [2].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [2] = "Empty";
			toadchoice ();
		    }
		}

		else if (x > 770 && x < 970 && y > 562 && y < 633) // fourth item, uses the item and then empties it
		{
		    if (!itemnames [3].equals ("Empty"))
		    {
			if (itemnames [3].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [3].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [3].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [3] = "Empty";
			toadchoice ();
		    }
		}
	    }
	}

	else if (screen == 21) // DEADPOOL BATTLE SCREEN (LAST SCREEN FOR BATTLING)
	{
	    text = 4;

	    if (playerhealth <= 0) // when the player dies
	    {
		screen = 19;
	    }
	    else if (enemyhealth [2] <= 0) // when the opponent dies
	    {
		text = 3;
		screen = 22;
		playerhealth = 115;
		healthy = 0;
		levelnow = 8;
		playerhealthshown = playerhealth * 4;
	    }

	    else if (playerhealth > 0 && enemyhealth [2] > 0) // when both characters are alive
	    {
		text = 1;

		if (x > 30 && x < 260 && y > 477 && y < 538) // attack 1
		{
		    if (attackvalue [0] > 0)
		    {
			enemyhealth [2] -= attackvalue [0];
			deadpoolchoice ();
		    }
		}
		else if (x > 30 && x < 260 && y > 560 && y < 630) // attack 2
		{
		    if (attackvalue [2] > 0)
		    {
			enemyhealth [2] -= attackvalue [2];
			deadpoolchoice ();
		    }
		}
		else if (x > 280 && x < 473 && y > 477 && y < 538) // attack 3
		{
		    if (attackvalue [1] > 0)
		    {
			enemyhealth [2] -= attackvalue [1];
			deadpoolchoice ();
		    }
		}
		else if (x > 280 && x < 473 && y > 560 && y < 630) // attack 4
		{
		    if (attackvalue [3] > 0)
		    {
			enemyhealth [2] -= attackvalue [3];
			deadpoolchoice ();
		    }
		}

		else if (x > 533 && x < 750 && y > 478 && y < 540)   // first item, uses the item and then empties it
		{
		    if (!itemnames [0].equals ("Empty"))
		    {
			if (itemnames [0].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [0].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [0].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [0] = "Empty";
			deadpoolchoice ();
		    }
		}

		else if (x > 770 && x < 970 && y > 478 && y < 540) // second item, uses the item and then empties it
		{
		    if (!itemnames [1].equals ("Empty"))
		    {
			if (itemnames [1].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [1].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;

			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;

			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;

			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [1].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [1] = ("Empty");
			deadpoolchoice ();
		    }
		}


		else if (x > 533 && x < 750 && y > 562 && y < 633) // third item, uses the item and then empties it
		{
		    if (!itemnames [2].equals ("Empty"))
		    {
			if (itemnames [2].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [2].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [2].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			    ;
			}
			itemnames [2] = "Empty";
			deadpoolchoice ();
		    }
		}

		else if (x > 770 && x < 970 && y > 562 && y < 633) // fourth item, uses the item and then empties it
		{
		    if (!itemnames [3].equals ("Empty"))
		    {
			if (itemnames [3].equals ("First Aid Kit"))
			{
			    if (healthy == 0)
				playerhealth = 115;
			    else if (healthy == 1)
				playerhealth = 230;
			}
			else if (itemnames [3].equals ("High Attacker"))
			{
			    if (attackvalue [0] > 0)
				attackvalue [0] += 20;
			    if (attackvalue [1] > 0)
				attackvalue [1] += 20;
			    if (attackvalue [2] > 0)
				attackvalue [2] += 20;
			    if (attackvalue [3] > 0)
				attackvalue [3] += 20;
			}
			else if (itemnames [3].equals ("HP UP"))
			{
			    playerhealth = 230;
			    healthy = 1;
			}
			itemnames [3] = "Empty";
			deadpoolchoice ();
		    }
		}
	    }
	}

	// tells the player that he/she won after beating deadpool (the boss)
	else if (screen == 22)
	{
	    screen = 23;
	}

	// the mission accomplished screen
	else if (screen == 23)
	{
	    if (x > 640 && x < 985 && y > 284 && y < 362) // the play again button
		resetfullgame ();
	}

	repaint ();
    }


    // when the user presses the mouse and holds it, the buttons change
    public void mousePressed (MouseEvent e)
    {
	// get the x and y co-ordinates of the mouse
	int x = e.getX ();
	int y = e.getY ();

	// TITLE SCREEN
	if (screen == 0)
	{
	    if (x > 148 && x < 359 && y > 575 && y < 618) // Play button
		TitleScreen = getImage (getDocumentBase (), "titleclickedplay.jpg");
	    else if (x > 668 && x < 883 && y > 578 && y < 620) // Instructions button
		TitleScreen = getImage (getDocumentBase (), "titleclickedinstructions.jpg");
	}

	//the menu screen
	else if (screen == 2)
	{
	    if (x > 44 && x < 258 && y > 70 && y < 133)
		MenuScreen1 = getImage (getDocumentBase (), "menuclickedcampaign.jpg");
	    else if (x > 40 && x < 258 && y > 270 && y < 334)
		MenuScreen1 = getImage (getDocumentBase (), "menuclickedprofile.jpg");
	    else if (x > 40 && x < 258 && y > 471 && y < 531)
		MenuScreen1 = getImage (getDocumentBase (), "menuclickedback.jpg");
	}

	// the intro screen
	else if (screen == 11)
	{
	    if (x > 820 && x < 987 && y > 577 && y < 638)
		IntroScreen = getImage (getDocumentBase (), "introscreenclickednext.jpg");
	    else if (x > 23 && x < 191 && y > 579 && y < 639)
		IntroScreen = getImage (getDocumentBase (), "introscreenclickedback.jpg");
	}
	repaint ();
    }


    public boolean check (int posx, int posy)  // For the House Map
    {
	//System.out.println (tracker [posy] [posx] + ", (" + posx + ", " + posy + ") AND " + xx + " " + yy + " ");
	//Checks if in bounds
	if (posx < 0 || posx > 25)
	    return false;
	else if (posy < 0 || posy > 18)
	    return false;
	//Checks if hits walls
	else if (tracker [posy] [posx] == 1 || tracker [posy] [posx] == 2 || tracker [posy] [posx] == 3 || tracker [posy] [posx] == 4 || tracker [posy] [posx] == 5 || tracker [posy] [posx] == 6)
	    return true;
	else
	    return false;
    }


    public boolean checkmap2 (int posx2, int posy2)  // for the HOUSE OUTSIDE MAP
    {
	//System.out.println ("(" + posx2 + ", " + posy2 + ") AND " + map2x + ", " + map2y + " ");
	if (posx2 < 0 || posx2 > 49)
	    return false;
	else if (posy2 < 0 || posy2 > 38)
	    return false;
	//Checks if hits walls
	else if (trackermap2 [posy2] [posx2] == 1 || trackermap2 [posy2] [posx2] == 2 || trackermap2 [posy2] [posx2] == 3 || trackermap2 [posy2] [posx2] == 4 || trackermap2 [posy2] [posx2] == 5 || trackermap2 [posy2] [posx2] == 6 || trackermap2 [posy2] [posx2] == 7 || trackermap2 [posy2] [posx2] == 8 || trackermap2 [posy2] [posx2] == 9)
	    return true;
	else
	    return false;
    }


    public boolean checkrivalmap (int posx4, int posy4)  // for the RIVAL HOUSE
    {
	//System.out.println ("(" + posx4 + ", " + posy4 + ") AND " + rivalx + ", " + rivaly + " ");
	if (posx4 < 0 || posx4 > 25)
	    return false;
	else if (posy4 < 0 || posy4 > 18)
	    return false;
	else if (trackerrivalhouse [posy4] [posx4] == 1 || trackerrivalhouse [posy4] [posx4] == 2)
	    return true;
	else
	    return false;
    }


    public boolean checkupstairs (int upposx, int upposy)  // for the UPSTAIRS
    {
	//System.out.println ("(" + upposx + ", " + upposy + ") AND " + upstairsx + ", " + upstairsy + " ");
	if (upposx < 0 || upposy > 25)
	    return false;
	else if (upposy < 0 || upposy > 17)
	    return false;
	else if (trackerupstairs [upposy] [upposx] == 1 || trackerupstairs [upposy] [upposx] == 2 || trackerupstairs [upposy] [upposx] == 3 || trackerupstairs [upposy] [upposx] == 4 || trackerupstairs [upposy] [upposx] == 5)
	    return true;
	else
	    return false;
    }


    public boolean checklab (int labposx, int labposy)  // for the LAB
    {
	//System.out.println ("(" + labposx + ", " + labposy + ") AND " + labx + ", " + laby + " ");
	if (labposx < 0 || labposx > 26)
	    return false;
	else if (labposy < 0 || labposy > 27)
	    return false;
	else if (trackerlab [labposy] [labposx] == 1 || trackerlab [labposy] [labposx] == 2 || trackerlab [labposy] [labposx] == 3)
	    return true;
	else
	    return false;
    }



    public void coordinates ()  // for main house
    {
	//System.out.println (item + "<---item and attack --->" + attack);
	if (tracker [posy] [posx] == 2) // checks to see if it has to navigate to another screen
	{
	    screen = 4;
	    map2x = 228;
	    map2y = 254;
	    posx2 = 13;
	    posy2 = 15;
	}


	else if (tracker [posy] [posx] == 3)   // checks to see if it has to navigate to another screen
	{
	    screen = 8;
	    upstairsx = 615;
	    upstairsy = 240;
	    upposx = 20;
	    upposy = 3;
	}


	else if (tracker [posy] [posx] == 4)   // checks to see if it there is an item
	{
	    if (housescreenchange == 1 && item3change == 0)
	    {
		item = 3;
		show = 0;
		screen = 16;
	    }
	    else
		;
	}


	else if (tracker [posy] [posx] == 5)   // checks to see if it there is an item
	{
	    if (housescreenchange == 1 && attack2change == 0)
	    {
		attack = 2;
		show = 1;
		screen = 16;
	    }
	    else
		;
	}


	else if (tracker [posy] [posx] == 6)   // checks to see if it there is an item
	{
	    if (housescreenchange == 1 && attack3change == 0)
	    {
		attack = 3;
		show = 1;
		screen = 16;
	    }
	    else
		;
	}
    }


    public void coordinatesmap2 ()  // for the MAIN MAP
    {
	if (trackermap2 [posy2] [posx2] == 2)
	{
	    screen = 3;
	    xx = 414;
	    yy = 415;
	    posx = 7;
	    posy = 16;
	}


	else if (trackermap2 [posy2] [posx2] == 3)   // checks to see if it has to navigate to another screen
	{
	    screen = 9;
	    rivalx = 444;
	    rivaly = 415;
	    posx4 = 9;
	    posy4 = 16;
	}


	else if (trackermap2 [posy2] [posx2] == 4)   // checks to see if it has to navigate to another screen
	{
	    screen = 10;
	    labx = 480;
	    laby = 480;
	    labposx = 13;
	    labposy = 25;
	}


	else if (trackermap2 [posy2] [posx2] == 5)   // checks to see if it there is an item
	{
	    if (housescreenchange == 0)
		screen = 13;
	    else
		screen = 17;
	}

	else if (trackermap2 [posy2] [posx2] == 6)   // checks to see if it there is an item
	{
	    if (housescreenchange == 1 && attack4change == 0)
	    {
		show = 1;
		attack = 4;
		screen = 16;
	    }
	}
	else if (trackermap2 [posy2] [posx2] == 7)   // checks to see if it there is an item
	{
	    if (housescreenchange == 1 && attack5change == 0)
	    {
		show = 1;
		attack = 5;
		screen = 16;
	    }
	}
	else if (trackermap2 [posy2] [posx2] == 8)   // checks to see if it there is an item
	{
	    if (housescreenchange == 1 && item4change == 0)
	    {
		show = 0;
		item = 4;
		screen = 16;
	    }
	}
	else if (trackermap2 [posy2] [posx2] == 9)   // checks to see if it there is an item
	{
	    if (housescreenchange == 1 && attack6change == 0)
	    {
		show = 1;
		attack = 6;
		screen = 16;
	    }
	}
    }


    public void coordinatesrival ()  // for the Rival House
    {
	if (trackerrivalhouse [posy4] [posx4] == 2)   // checks to see if it has to navigate to another screen
	{
	    screen = 4;
	    map2x = 498;
	    map2y = 254;
	    posx2 = 31;
	    posy2 = 15;

	}
    }


    public void coordinatesupstairs ()  //for the UPSTAIRS
    {
	//System.out.println (item + "<---item and attack --->" + attack);
	if (trackerupstairs [upposy] [upposx] == 2)   // checks to see if it has to navigate to another screen
	{
	    screen = 3;
	    xx = 594;
	    yy = 220;
	    posx = 19;
	    posy = 3;
	}


	else if (trackerupstairs [upposy] [upposx] == 3)
	{
	    if (housescreenchange == 1 && item1change == 0)   // checks to see if it there is an item
	    {
		item = 1;
		show = 0;
		screen = 16;
	    }
	    else
		;
	}


	else if (trackerupstairs [upposy] [upposx] == 4)
	{
	    if (housescreenchange == 1 && attack1change == 0)   // checks to see if it there is an item
	    {
		attack = 1;
		show = 1;
		screen = 16;
	    }
	    else
		;
	}


	else if (trackerupstairs [upposy] [upposx] == 5)
	{
	    if (housescreenchange == 1 && item2change == 0)   // checks to see if it there is an item
	    {
		item = 2;
		show = 0;
		screen = 16;
	    }
	    else
		;
	}
    }


    public void coordinateslab ()  // for the LAB
    {
	if (trackerlab [labposy] [labposx] == 2)
	{
	    screen = 4;
	    map2x = 528;
	    map2y = 429;
	    posx2 = 33;
	    posy2 = 27;
	}


	else if (trackerlab [labposy] [labposx] == 3)   // checks to see if the player has already chosen the first attack
	{
	    if (labscreenchange == 0)
		screen = 14;
	    else
		;
	}
    }


    public void InitImages ()
    { // Pre: All the images are there
	// Post: All the images are drawn offscreen, to ensure minimal load times during gameplay.

	Graphics g = getGraphics ();

	//put in comments because it wastes space

	//g.drawImage (TitleScreen, -100, -100, this);
	g.drawImage (InstructionScreen, -100, -100, this);
	g.drawImage (MenuScreen1, -100, -100, this);
	//g.drawImage (PlayScreen1, -100, -100, this);
	//g.drawImage (Chara, -100, -100, this);
	g.drawImage (YuriBattle, -100, -100, this);
	//g.drawImage (PlayScreen2, -100, -100, this);
	//g.drawImage (Upstairs, -100, -100, this);
	//g.drawImage (Rivalhouse, -100, -100, this);
	//g.drawImage (LabScreen, -100, -100, this);
	//g.drawImage (IntroScreen, -100, -100, this);
	//g.drawImage (IntroScreenUpstairs, -100, -100, this);
	g.drawImage (GoToLab, -100, -100, this);
	g.drawImage (chooseAttack, -100, -100, this);
	g.drawImage (treasureHunt, -100, -100, this);
	g.drawImage (switchingScreen, -100, -100, this);
	//g.drawImage (readyBattle, -100, -100, this);
	//g.drawImage (nextBattle, -100, -100, this);
	//g.drawImage (DeadScreen, -100, -100, this);
	//g.drawImage (toadBattle, -100, -100, this);
	//g.drawImage (deadpoolBattle, -100,-100,this);
	//g.drawImage (missionAccomplished, -100, -100,this);
	//g.drawImage (InstructionScreen2, -100, -100, this);
	//g.drawImage (InstructionScreen3, -100, -100, this);

    }


    public void update (Graphics g)
    { // Overide the regular update method so it doesn't clear the screen before it draws (Gets rid of annoying white flicker)
	paint (g);
    }


    public void paint (Graphics g)
    {
	if (screen == 0) // Menu
	{
	    g.drawImage (TitleScreen, 0, 0, this);
	    showStatus ("Welcome To Scaver's Creed " + name + "!");
	}


	else if (screen == 1) // InstructionScreen
	{
	    g.drawImage (InstructionScreen, 0, 0, this);
	    showStatus ("Instructions");
	}


	else if (screen == 2) // MenuScreen1
	{
	    g.drawImage (MenuScreen1, 0, 0, this);
	    showStatus ("Menu");
	}


	else if (screen == 3) // firstplayingscreen
	{
	    g.drawImage (PlayScreen1, 0, 0, this);
	    g.drawImage (Chara, xx, yy, this);

	    showStatus ("Scaver's Creed");
	}


	else if (screen == 4) // MAIN VILLAGE MAP
	{
	    g.drawImage (PlayScreen2, 0, 0, this);
	    g.drawImage (Chara, map2x, map2y, this);
	}


	else if (screen == 5) // profilescreen
	{
	    g.setFont (new Font ("Algerian", Font.PLAIN, 35));
	    g.drawImage (ProfileScreen, 0, 0, this);

	    g.setColor (new Color (155, 0, 0));
	    g.drawString (name + " ", 290, 100);

	    g.setColor (Color.darkGray);                // the background grey rectangle for the XP
	    g.fillRect (530, 410, 400, 20);
	    g.setColor (new Color (131, 0, 0));         // the red overlay rectangle for the XP

	    if (xp >= 400 && levelnow < 8)
	    {
		xp = 0;
		levelnow++;
	    }

	    //System.out.println (xp + " ");
	    g.fillRect (530, 410, xp, 20);
	    g.setColor (Color.gray);                    // the background grey rectangle for the steps taken
	    stepsshown = stepstaken / 2;


	    g.fillRect (62, 405, 400, 20);

	    g.setColor (new Color (131, 0, 0));         // the red color for the overlay rectangle for the steps taken
	    g.fillRect (62, 405, stepsshown, 20);       // the overlay rectangle for the steps taken

	    g.setColor (Color.white);
	    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
	    g.drawString (stepstaken + " steps", 237, 420);     // shows the amount of steps taken inside the rectangle

	    int xpnextlevel = 400 - xp;
	    g.drawString (levelnow + "", 530, 445);     // shows the level that the user is at the moment

	    if (levelnow < 8)
		g.drawString ((levelnow + 1) + "", 925, 445);   // shows the upcoming level
	    else if (levelnow >= 8)
	    {
		g.setFont (new Font ("Times New Roman", Font.BOLD + Font.ITALIC, 20));
		g.drawString ("Game Finished", 820, 450);
	    }

	    g.setColor (Color.white);
	    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
	    g.drawString (xpnextlevel + " xp needed", 680, 425);  // prints the amount of xp needed to get to the next level

	    Date now = new Date ();
	    DateFormat df = DateFormat.getDateInstance ();
	    String s = df.format (now);
	    g.drawString ("Today is " + s, 670, 525);

	    if (i2 == 60)
	    {
		i3++;
		i2 = 0;
	    }

	    g.setFont (new Font ("Times New Roman", Font.BOLD, 20));

	    g.drawString (i3 + " Hours   " + i2 + " Minutes   " + i + " Seconds", 160, 525);

	    if (stepstaken > 800)                       // make sure that the red overlay doesn't extend over the end of the gray background
	    {
		stepstaken = 800;
		g.setColor (Color.black);
		g.setFont (new Font ("Times New Roman", Font.BOLD + Font.ITALIC, 20));
		g.drawString ("Challenge Completed", 62, 440);          // shows that the challenge is completed if the user exceeds 800 steps
	    }
	}

	// Yuri Battle Screen
	else if (screen == 7)
	{
	    g.drawImage (YuriBattle, 0, 0, this);


	    g.setColor (Color.black);
	    g.setFont (new Font ("Algerian", Font.PLAIN, 130));
	    if (text == 0)
		g.drawString (charactername + " VS YURI", 80, 325);

	    showStatus ("Battle Time");

	    g.setColor (new Color (56, 56, 48));
	    g.setFont (new Font ("Aharoni", Font.PLAIN, 25));

	    /*attacknames [0] = "1";
	    attacknames [1] = "2";
	    attacknames [2] = "3";
	    attacknames [3] = "4";*/
	    g.drawString (attacknames [0] + " ", 50, 520);
	    g.drawString (attacknames [1] + " ", 290, 520);
	    g.drawString (attacknames [2] + " ", 50, 600);
	    g.drawString (attacknames [3] + " ", 290, 600);

	    g.drawString (itemnames [0] + " ", 550, 515);
	    g.drawString (itemnames [1] + " ", 780, 515);
	    g.drawString (itemnames [2] + " ", 550, 600);
	    g.drawString (itemnames [3] + " ", 780, 600);


	    g.setFont (new Font ("Aharoni", Font.PLAIN, 18));
	    g.drawString (attackvalue [0] + " damage", 50, 495);
	    g.drawString (attackvalue [1] + " damage", 290, 495);
	    g.drawString (attackvalue [2] + " damage", 50, 575);
	    g.drawString (attackvalue [3] + " damage", 290, 575);

	    g.setColor (new Color (108, 86, 73));

	    if (healthy == 0)
		playerhealthshown = playerhealth * 4;
	    else if (healthy == 1)
		playerhealthshown = playerhealth * 2;

	    g.fillRect (21, 398, playerhealthshown, 19);

	    int yurihealthshown = enemyhealth [0] * 4;
	    g.fillRect (520, 398, yurihealthshown, 19);

	    g.setColor (new Color (56, 56, 48));
	    g.setFont (new Font ("Algerian", Font.PLAIN, 18));
	    if (playerhealth <= 0)
		playerhealth = 0;
	    g.drawString (playerhealth + " HP", 230, 415);

	    if (enemyhealth [0] < 0)
		enemyhealth [0] = 0;
	    g.drawString (enemyhealth [0] + " HP", 720, 415);



	}


	else if (screen == 8) // upstairsx, upstairsy, posx3, posy3
	{
	    g.drawImage (Upstairs, 0, 0, this);
	    g.drawImage (Chara, upstairsx, upstairsy, this);
	}


	else if (screen == 9) //rivalx, rivaly, posx4, posy4
	{
	    g.drawImage (Rivalhouse, 0, 0, this);
	    g.drawImage (Chara, rivalx, rivaly, this);
	}

	// Lab Screen map
	else if (screen == 10)
	{
	    g.drawImage (LabScreen, 0, 0, this);
	    g.drawImage (Chara, labx, laby, this);
	}

	// Intro Screen
	else if (screen == 11)
	{
	    g.drawImage (IntroScreen, 0, 0, this);
	}

	// AHH THAT WAS A NICE NAP
	else if (screen == 12)
	{
	    g.drawImage (IntroScreenUpstairs, 0, 0, this);
	    g.drawImage (Chara, upstairsx, upstairsy, this);

	    g.setFont (new Font ("Times New Roman", Font.PLAIN, 12));
	    g.setColor (Color.white);
	    g.drawString ("Click Anywhere to Continue", 445, 460);

	}

	// GO to the lab to start battles
	else if (screen == 13)
	{
	    g.drawImage (GoToLab, 0, 0, this);
	    g.drawImage (Chara, map2x, map2y, this);

	    g.setFont (new Font ("Times New Roman", Font.PLAIN, 13));
	    g.setColor (Color.white);
	    g.drawString ("Click Anywhere to Continue", 300, 640);
	}

	// choosing your first attack
	else if (screen == 14)
	{
	    g.drawImage (chooseAttack, 0, 0, this);
	}

	// tells the player that there are several items that are hidden around the village
	else if (screen == 15)
	{
	    g.drawImage (treasureHunt, 0, 0, this);

	    g.setFont (new Font ("Times New Roman", Font.PLAIN, 13));
	    g.setColor (Color.white);
	    g.drawString ("Click Anywhere to Continue", 420, 540);
	}

	// Pops up when an item or attack is picked up
	else if (screen == 16)
	{
	    g.drawImage (switchingScreen, 0, 0, this);

	    if (show == 0) // shows the items stuff
	    {

		g.setFont (new Font ("Times New Roman", Font.BOLD, 35));
		g.setColor (new Color (178, 241, 246));
		g.drawString (itemnames [0] + " ", 133, 355);
		g.drawString (itemnames [1] + " ", 577, 355);
		g.drawString (itemnames [2] + " ", 136, 460);
		g.drawString (itemnames [3] + " ", 577, 460);

		g.setFont (new Font ("Times New Roman", Font.BOLD, 48));
		g.setColor (new Color (178, 241, 246));

		if (item == 1)
		{
		    g.drawString ("First Aid Kit", 500, 70);
		}
		else if (item == 2)
		{
		    g.drawString ("High Attacker", 500, 70);
		}
		else if (item == 3)
		{
		    g.drawString ("First Aid Kit", 500, 70);
		}
		else if (item == 4)
		{
		    g.drawString ("HP UP", 500, 70);
		}
	    }
	    else if (show == 1) //shows the attacks stuff
	    {
		g.setFont (new Font ("Times New Roman", Font.BOLD, 35));
		g.setColor (new Color (178, 241, 246));
		g.drawString (attacknames [0] + " ", 133, 355);
		g.drawString (attacknames [1] + " ", 577, 355);
		g.drawString (attacknames [2] + " ", 136, 460);
		g.drawString (attacknames [3] + " ", 577, 460);

		g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
		g.drawString (attackvalue [0] + " damage", 133, 320);
		g.drawString (attackvalue [1] + " damage", 577, 320);
		g.drawString (attackvalue [2] + " damage", 136, 427);
		g.drawString (attackvalue [3] + " damage", 577, 427);

		g.setFont (new Font ("Times New Roman", Font.BOLD, 48));
		g.setColor (new Color (178, 241, 246));

		if (attack == 1)
		{
		    g.drawString ("Shatter Blast", 500, 70);
		    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
		    g.drawString ("30 Damage", 780, 70);
		}
		else if (attack == 2)
		{
		    g.drawString ("Alpha Bolt", 500, 70);
		    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
		    g.drawString ("20 Damage", 750, 70);
		}
		else if (attack == 3)
		{
		    g.drawString ("Graviton Blast", 500, 70);
		    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
		    g.drawString ("25 Damage", 810, 70);
		}
		else if (attack == 4)
		{
		    g.drawString ("FireStorm", 500, 70);
		    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
		    g.drawString ("20 Damage", 730, 70);
		}
		else if (attack == 5)
		{
		    g.drawString ("Punch", 500, 70);
		    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
		    g.drawString ("5 Damage", 655, 70);
		}
		else if (attack == 6)
		{
		    g.drawString ("Ionic Storm", 500, 70);
		    g.setFont (new Font ("Times New Roman", Font.BOLD, 15));
		    g.drawString ("50 Damage", 750, 70);
		}
	    }
	}

	// asks the player if he/ she is ready to battle
	else if (screen == 17)
	{
	    g.drawImage (readyBattle, 0, 0, this);
	}

	// when an opponent dies
	else if (screen == 18)
	{
	    g.drawImage (nextBattle, 0, 0, this);
	    g.setColor (Color.white);
	    g.setFont (new Font ("Algerian", Font.PLAIN, 130));
	    if (text == 2 || text == 3 || text == 4)
		g.drawString ("YOU WON!", 220, 150);
	}

	// when the player dies
	else if (screen == 19)
	{
	    g.drawImage (DeadScreen, 0, 0, this);
	}


	else if (screen == 20)  ///TOAD battle screen
	{
	    g.drawImage (toadBattle, 0, 0, this);

	    g.setColor (Color.black);
	    g.setFont (new Font ("Algerian", Font.PLAIN, 130));
	    if (text == 2)
		g.drawString (charactername + " VS TOAD", 80, 325);


	    showStatus ("Battle Time");

	    g.setColor (new Color (56, 56, 48));
	    g.setFont (new Font ("Aharoni", Font.PLAIN, 25));

	    /*attacknames [0] = "1";
	    attacknames [1] = "2";
	    attacknames [2] = "3";
	    attacknames [3] = "4";*/
	    g.drawString (attacknames [0] + " ", 50, 520);
	    g.drawString (attacknames [1] + " ", 290, 520);
	    g.drawString (attacknames [2] + " ", 50, 600);
	    g.drawString (attacknames [3] + " ", 290, 600);

	    g.drawString (itemnames [0] + " ", 550, 515);
	    g.drawString (itemnames [1] + " ", 780, 515);
	    g.drawString (itemnames [2] + " ", 550, 600);
	    g.drawString (itemnames [3] + " ", 780, 600);


	    g.setFont (new Font ("Aharoni", Font.PLAIN, 18));
	    g.drawString (attackvalue [0] + " damage", 50, 495);
	    g.drawString (attackvalue [1] + " damage", 290, 495);
	    g.drawString (attackvalue [2] + " damage", 50, 575);
	    g.drawString (attackvalue [3] + " damage", 290, 575);

	    g.setColor (new Color (108, 86, 73));

	    if (healthy == 0)
		playerhealthshown = playerhealth * 4;
	    else if (healthy == 1)
		playerhealthshown = playerhealth * 2;

	    g.fillRect (21, 398, playerhealthshown, 19);

	    int toadhealthshown = enemyhealth [1] * 2;
	    g.fillRect (520, 398, toadhealthshown, 19);

	    g.setColor (new Color (56, 56, 48));
	    g.setFont (new Font ("Algerian", Font.PLAIN, 18));
	    if (playerhealth <= 0)
		playerhealth = 0;
	    g.drawString (playerhealth + " HP", 230, 415);

	    if (enemyhealth [1] < 0)
		enemyhealth [1] = 0;
	    g.drawString (enemyhealth [1] + " HP", 720, 415);


	}
	else if (screen == 21) //dead pool battle screen
	{
	    g.drawImage (deadpoolBattle, 0, 0, this);

	    g.setColor (Color.black);
	    g.setFont (new Font ("Algerian", Font.PLAIN, 100));
	    if (text == 3)
		g.drawString (charactername + " VS DEADPOOL", 70, 325);

	    showStatus ("Battle Time");

	    g.setColor (new Color (56, 56, 48));
	    g.setFont (new Font ("Aharoni", Font.PLAIN, 25));

	    /*attacknames [0] = "1";
	    attacknames [1] = "2";
	    attacknames [2] = "3";
	    attacknames [3] = "4";*/
	    g.drawString (attacknames [0] + " ", 50, 520);
	    g.drawString (attacknames [1] + " ", 290, 520);
	    g.drawString (attacknames [2] + " ", 50, 600);
	    g.drawString (attacknames [3] + " ", 290, 600);

	    g.drawString (itemnames [0] + " ", 550, 515);
	    g.drawString (itemnames [1] + " ", 780, 515);
	    g.drawString (itemnames [2] + " ", 550, 600);
	    g.drawString (itemnames [3] + " ", 780, 600);


	    g.setFont (new Font ("Aharoni", Font.PLAIN, 18));
	    g.drawString (attackvalue [0] + " damage", 50, 495);
	    g.drawString (attackvalue [1] + " damage", 290, 495);
	    g.drawString (attackvalue [2] + " damage", 50, 575);
	    g.drawString (attackvalue [3] + " damage", 290, 575);

	    g.setColor (new Color (108, 86, 73));

	    if (healthy == 0)
		playerhealthshown = playerhealth * 4;
	    else if (healthy == 1)
		playerhealthshown = playerhealth * 2;

	    g.fillRect (21, 398, playerhealthshown, 19);

	    int deadpoolhealthshown = enemyhealth [2] * 1;
	    g.fillRect (520, 398, deadpoolhealthshown, 19);

	    g.setColor (new Color (56, 56, 48));
	    g.setFont (new Font ("Algerian", Font.PLAIN, 18));
	    if (playerhealth <= 0)
		playerhealth = 0;
	    g.drawString (playerhealth + " HP", 230, 415);

	    if (enemyhealth [2] < 0)
		enemyhealth [2] = 0;
	    g.drawString (enemyhealth [2] + " HP", 720, 415);
	}



	else if (screen == 22) //after beating deadpool
	{
	    g.setColor (Color.white);
	    g.setFont (new Font ("Algerian", Font.PLAIN, 35));
	    g.drawString ("GOOD JOB " + charactername + ", YOU HAVE BEATEN THE SCAVER MASTER!", 40, 325);
	}
	// after the game is finished
	else if (screen == 23)
	{
	    g.drawImage (missionAccomplished, 0, 0, this);
	}
	// the second instruction screen
	else if (screen == 24)
	{
	    g.drawImage (InstructionScreen2, 0, 0, this);
	}
	// the third instruction screen
	else if (screen == 25)
	{
	    g.drawImage (InstructionScreen3, 0, 0, this);
	}
    }
}