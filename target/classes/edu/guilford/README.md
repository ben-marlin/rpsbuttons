# Rock Paper Scissors With Buttons

## Sample Code

You've been provided with a working copy of the rock / paper / scissors project you completed earlier in the semester for reference. It should work fine, although it operates on text input. Run the code and review how it works. Note that the documentation (comments) is meant to help you see which code does what.

## Coding a Visual Rock / Paper / Scissors

In this project, you will use

- a frame
- multiple layouts
- multiple panels
- labels with icons
- buttons 
- an action listener
- an array
- randomizer
- the resource directory

## A Screenshot

Keeping an idea of what we're heading towards will help us.
![Screenshot](screenshot.png)

## Create the Class

I named mine RPS and will work with that name going forward. I suggest you do the same. You've done this part multiple times.

Don't forget to comment your name in the top matter!

## Imports

We'll use the following imports in this project. 
```
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
```
None of these should be new to you!

## Included Files

Your repo will come with the image files `blank.png`, `leftpaper.png`, etc. saved in your `resource` folder. This is standard practice in Java - any files that are not Java code is saved in this folder. Unfortunately, reference to these files in any context is pretty arcane, but putting them in the resources folder makes it at least understandable.

One difficulty is caused by the nature of Java. In most languages, code is compiled by a compiler completely set up for the particular machine it's on (if this doesn't match your experience with video games, suffice to say I'm avoiding a lot of detail here). Java instead works with something called the "Java Virtual Machine" that makes the same code run on any platform. Consequently, as your code is compiled, a copy is made... somewhere. We have to trust the Virtual Machine to handle the location of resources in a consistent manner.

## Adding References to Your Files

Add the following code inside your class. This approach is meant to make later code a bit easier to follow. If you later choose to use different images for your project, you'll have all these references in one place.
```
// collecting images here - if you change pictures, have them all in the resources folder
ImageIcon BLANK = new ImageIcon(RPS.class.getClassLoader().getResource("blank.png"));
ImageIcon LEFTROCK = new ImageIcon(RPS.class.getClassLoader().getResource("leftrock.png"));
ImageIcon LEFTPAPER = new ImageIcon(RPS.class.getClassLoader().getResource("leftpaper.png"));
ImageIcon LEFTSCISSORS = new ImageIcon(RPS.class.getClassLoader().getResource("leftscissors.png"));
ImageIcon RIGHTROCK = new ImageIcon(RPS.class.getClassLoader().getResource("rightrock.png"));
ImageIcon RIGHTPAPER = new ImageIcon(RPS.class.getClassLoader().getResource("rightpaper.png"));
ImageIcon RIGHTSCISSORS = new ImageIcon(RPS.class.getClassLoader().getResource("rightscissors.png"));

ImageIcon[] computerChoices = {RIGHTROCK, RIGHTPAPER, RIGHTSCISSORS};
```
If you view the images, you'll see that the left & right plays are facing one another to make it look more "real". Thus having names referring to left & right makes sense. These were all clipped from rockpaperscissors.jpg, which I found from a search on images.google.com.

The complicated code is worth understanding. RPS is the class we are currently working on. As such, any class will read out its path (where it is on your computer) by using ClassName.class.getClassLoader(). Since we want something in the resource directory, we add .getResource("imagefile.png"). We'll use this sort of approach again later.

This simplifies a lot of things. Look through your device and figure out where you saved your repo. Then do the same for the person to your left and the one to your right. Unless you're identical triplets using identical devices and taking all the same classes, those were almost undoubtedly three different paths! Not to mention dealing with both Mac & Windows.

For later use, we declare an array of image icons here to avoid waste of resources in the listener object.

## Adding Fields

After the file names, add the following code.
```
private JLabel playerIconLabel;     // used for pictures
private JLabel computerIconLabel;

private JButton rockButton;         // play buttons
private JButton paperButton;
private JButton scissorsButton;

private JButton resetButton;        // clear plays

Random random = new Random(); // common randomizer
```
With the screenshot in mind, these should all make sense. We need them to be available to both the constructor and the listener for the buttons, so we declare them outside either. The randomizer only appears in the listener, but we don't want to re-instantiate the resource repeatedly for use of resources and certain oddities of random number generation.

## Make the Constructor

Don't bother having VSCode do this for you! The way you'll add it is sufficiently convoluted to make that pointless. Start by adding a basic constructor.
```
public RPS() {
    setTitle("Rock Paper Scissors");
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    setVisible(true);
}
```
You've seen this in past projects. We want to keep the visibility statement as the last of the constructor. All the constructor code will be inserted between the layout and visibility lines.

## Create *main* Method

In the class after the constructor but still inside the class, insert a main method. The only statement inside main should be `new RPS();`. All this will do is instantiate an object from the class. This is always necessary.

You should be able to test run your program at this point.

## Display Objects

Insert the following code into the constructor between the layout and visibility lines.
```
// used for labels
private JLabel playerLabel = new JLabel("Player", SwingConstants.CENTER);
private JLabel computerLabel = new JLabel("Computer", SwingConstants.CENTER);

// Initialize labels with icons
playerIconLabel = new JLabel(BLANK);
playerIconLabel.setOpaque(true);

computerIconLabel = new JLabel(BLANK);
computerIconLabel.setOpaque(true);

```
The icon labels will display the pictures of rock, paper, or scissors, but are initially blank. The other labels help the player know which play is theirs. SwingConstants.CENTER is an option that tells the label to center its contents. The setOpaque(true) will make labels that are transparent to their backgrounds not transparent. If you find new icons that are transparent, comment these lines out.

At this point, your code should compile, but won't do anything because we haven't added anything to the frame.

## Placing the Center Panel

These visual items could be added to the frame directly, but the display would look awful. In order to organize, we're going to make a center panel and add a left and right panel to it. Add the following code after the instantiation of the text labels but before the visibility line.
```
// Create center panel for labels
JPanel displayPanel = new JPanel();
displayPanel.setLayout(new GridLayout(1, 2));

JPanel leftPanel = new JPanel(new BorderLayout());
leftPanel.add(playerIconLabel,BorderLayout.CENTER);
leftPanel.add(playerLabel, BorderLayout.SOUTH);
displayPanel.add(leftPanel);

JPanel rightPanel = new JPanel(new BorderLayout());
rightPanel.add(computerIconLabel, BorderLayout.CENTER);
rightPanel.add(computerLabel, BorderLayout.SOUTH);
displayPanel.add(rightPanel);

// Add panel to frame
add(displayPanel, BorderLayout.CENTER);
```
Note displayPanel is really just a placeholder. It has one row and two columns - each space gets a panel of its own.

Then leftPanel gets a border layout so the icon label in the CENTER can be large while the label in the SOUTH will be relatively small. If we had used a grid layout for this panel, it would have made the two labels the same size, which wouldn't look very good. Both of these represent the player.

The rightPanel is identical, but represents the computer. Both panels are added to displayPanel. And displayPanel gets added to the frame - notice the frame has no need for a name here, it's just the object holding the displayPanel.

At this point, you should be able to test run your code. It lacks buttons, of course, but it should give you a rough impression of how the finished product will look. You might change BLANK to LEFTROCK on them to see how those images will appear. Change them back after, though!

## Adding the Button Panel

Let's add the buttons to the frame before we actually make them function. Place this after the line that added displayPanel but before the visibility line.
```
// Initialize buttons
rockButton = new JButton("Rock");
paperButton = new JButton("Paper");
scissorsButton = new JButton("Scissors");
resetButton = new JButton("Reset");

// Create panel for buttons with 1x4 grid layout
JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new GridLayout(1, 4));

// Add the buttons
buttonPanel.add(rockButton);
buttonPanel.add(paperButton);
buttonPanel.add(scissorsButton);
buttonPanel.add(resetButton);

// Add panel to frame
add(buttonPanel, BorderLayout.SOUTH);
```
Hopefully the comments are self-explanatory. We're using a 4x1 grid so the buttons will line up along the bottom. We could probably have used FlowLayout as well, but when you resize a container with a flow layout, it can place things in a messy way.

At this point, you should test run your code. It should look pretty much like the screenshot. Again, if you want to see the other icons, make changes to the code, but change them back after.

## Creating the Listener

Between the constructor and the main method, add the following private class.
```
private class ButtonClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // randomize computer choice
        ImageIcon computerChoice = computerChoices[random.nextInt(computerChoices.length)];
        
        if (e.getSource() == rockButton) {
            playerIconLabel.setIcon(LEFTROCK);
            computerIconLabel.setIcon(computerChoice);
        } else if (e.getSource() == paperButton) {
            playerIconLabel.setIcon(LEFTPAPER);
            computerIconLabel.setIcon(computerChoice);
        } else if (e.getSource() == scissorsButton) {
            playerIconLabel.setIcon(LEFTSCISSORS);
            computerIconLabel.setIcon(computerChoice);
        } else if (e.getSource() == resetButton) {
            playerIconLabel.setIcon(BLANK);
            computerIconLabel.setIcon(BLANK);
        }
    }
}
```
As in the coin flip example, we create a private class implementing ActionListener - specifically meant to interact with buttons & textfields - that will run when any of the buttons is clicked. Notice that here we're giving the private class a name - some practice would make such a private class "anonymous", but the logic is harder to follow when you do that.

The randomizer generates a number 0 to 2, and this icon is chosen for the computer's play. Since all we're doing is displaying, we don't need anything more complicated than this. Notice that this avoids a complicated if / else or switch structure.

Here we get to see a nice trick. Since this is a listener for four different buttons, we need to know which caused the event. We could also have handled this by creating four separate private classes, but this is  more readable. Since each button is an object, e.getSource() will match whichever one sent it, so we can use the if / else structure to run different code.

Note that each is very similar. We set the icon for the player's label to the appropriate image, then we set the computer label's icon to the random choice made earlier.

It would be worthwhile to test run your code now, even though it doesn't do anything. This is just to spot any copying errors you may have made.

## Connecting the Butttons to the Listener

At this point, you have all the pieces, but they don't talk to each other. Add the following code to the constructor after the line that added buttonPanel, but before the visibility line. 
```
// Add action listeners to buttons
rockButton.addActionListener(new ButtonClickListener());
paperButton.addActionListener(new ButtonClickListener());
scissorsButton.addActionListener(new ButtonClickListener());
resetButton.addActionListener(new ButtonClickListener());
```
An oddity here is that each new ButtonClickListener() will create a separate location in memory. This is the reason we avoided declaring variables in that class or creating a randomizer. This is unlikely to be a problem for our tiny application, but consider a bigger project with multiple stages, like tax preparation software. There might be hundreds of buttons and relatively large image files being dealt with.

At this point, your program should function. It doesn't decide who the winner is, but it throws against you and lets you clear the board.

## Embellishments

If you have time, try this: rather than creating a new ButtonListener for each button, declare a single button listener, then add it to each button. This saves a little bit of computer memory - you'll never notice it on small applications, but it can make a difference for large ones.

You'll probably notice the image background doesn't match the panels. Consider using setBackground(Color.WHITE) to fix this. Or, if you have access to image editing software, paint the png files to match whatever background you want.

If you're interested, you could expand the app. If you create another label for the NORTH pane, you can write code inside each if clause to say things like "Paper covers rock, you win!" and "Rock breaks scissors, you lose!" and "Scissors vs. scissors - a draw!". It will require a 3 possibility if / else or switch in each case... Don't forget to set the text of the label to the empty string if the reset button is pressed, though!

The images I chose are pretty basic. If you can find some nicer ones, it would be great. And I didn't make a great effort to get a pleasing color scheme - just basic gray. Exercise your creativity!

## Wrapping Up

You know the refrain. Save. Stage. Commit message. Commit button. Sync button. Screenshot & repo URL to Canvas.