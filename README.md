# CodeAlpha_AI_Chatbot
An intelligent chat bot using natural language processing and machine learning for interactive and automated communication.
## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Training Data](#training-data)
- [Code Explanation](#code-explanation)
- [Contributing](#contributing)
- [License](#license)

## Features
- Categorizes user input into predefined categories.
- Responds to user input based on the identified category.
- Interactive console-based chat interface.

## Installation
1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/ai-chatbot.git
    cd ai-chatbot
    ```
2. **Ensure you have Java and Maven installed**.
3. **Install dependencies**:
    ```sh
    mvn install
    ```

## Usage
1. **Prepare your training data**:
   - Create a file named `trainingData.txt` with your training examples. See the [Training Data](#training-data) section for the format.
2. **Run the application**:
    ```sh
    mvn exec:java -Dexec.mainClass="com.ai.chatbot.App"
    ```
3. **Interact with the chatbot**:
    - Type your messages in the console.
    - Type `exit` to quit the chat.

## Training Data
The training data file (`trainingData.txt`) should contain examples for each category, formatted as follows:
greeting    hey there
greeting    howdy
greeting    what's up
goodbye    goodbye
goodbye    see you
goodbye    see you later

