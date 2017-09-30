# Plagiarism Checker
This tool calculates the similarity value between two texts.

## Notes
- Percentage is rounded to 2 decimal places.

## Assumptions Made
- Only checking the similarities between the words and not punctuations/numbers.
- Words are separated by whitespace.
- We also assume that each word belongs to one synonym group.
- The code ignores cases by design.

## Test Cases

- The directory 1 contains test cases identical to the ones provided in the document.
It will output 100%.

    - If you provide a tuple size > 4 in this test case, we should see an error message displayed.
    ```$xslt
    The tuple size that you provided is greater than the amount of words in the file.
    
    Process finished with exit code 255
    ```
    
    - If you provide a tuple size < 0 in this test case, we should see an error message displayed.
    ```$xslt
    You've provided an invalid tuple size. Must be > 0
    
    Process finished with exit code 255
    ```

- The directory 2 contains test cases that will result in 0% similarity.

- The directory 3 contains test cases that will also result in 0% similarity.

- The directory 4 contains test cases that will result in 50% similarity.

- The directory 5 contains test cases that will result in 100% similarity. This checks for
the case where the cases of the characters are different, ensuring that it ignores them 
appropriately.

- The directory 6 contains test cases that will result in an error message being displayed.
One of the input files provided contains no text, expected message is 
```$xslt
No valid content found in the file that you provided.

Process finished with exit code 255

```

- The directory 7 contains test cases that will result in an error message being displayed.
The synonym file provided contains no text, expected message is
```$xslt
Synonym map is empty when it shouldn't be.

Process finished with exit code 255

```