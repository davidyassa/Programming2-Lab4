# Github Repository Instructions

### 1 - Create your branch
1. Next to `main↓`, click `branches`
2. Click `New branch`
3. Choose `devel` as the source, and name it `space-` + your name
4. Create new branch

### 2 - Access Github from Netbeans
1. Install [Git](https://github.com/git-for-windows/git/releases/download/v2.51.0.windows.2/Git-2.51.0.2-64-bit.exe)
2. Clone Repository
     1. Head to a folder of your choice and within the folder: "Right click > open in terminal"
     2. In the terminal, type:  
            `git clone https://github.com/davidyassa/Programming2-Lab5.git` 
     3. Reopen the terminal window inside the repo folder, then type: `git checkout ` + your branch name
3. Open Netbeans then go to "File > Open project" and choose the local repo folder

### 3 - Entering GitHub user&pass in Netbeans

1. Go to your GitHub profile settings in the top right corner
2. Scroll down on the left pane to "<> developer settings"
3. "Personal Access Tokens > Tokens (classic) > Generate new token (classic)"
4. Confirm Access with password or passkey
5. Name the key (e.g. Netbeans)
6. Set expiration date (no expiration if you want this to be one-time)
7. Select Scopes → `repo`, which gives you access to all your GitHub repos
8. Scroll to the end to "Generate new token"
9. Copy the given key (long code) and paste it in the "password" value in Netbeans


### 4 - Workflow:  
1. Before working, pull changes from `devel`  (development branch)
    - "Git > Pull > devel"
2. After you're done coding, add your files to be comitted
    - "Git > Add"
3. Commit your changes and `push` them to your branch 
    - "Git > Commit" and enter a brief commit message
4. Push your changes to your branch  
    - "Git > Remote > Push" and choose _your_ branch
5. Create a pull request from your branch to `devel`
    - Back on Github, open a pull request
    - At the top choose "`devel` ← `your branch`"
    - add a brief comment
7. Once the merge request is approved, `devel` will be up to date with your code
8. When the project reaches its very final stage, it will be merged onto `main`