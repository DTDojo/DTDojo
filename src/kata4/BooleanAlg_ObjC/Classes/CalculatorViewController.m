//
//  CalculatorViewController.m
//
//  Created by Neal Wiggins on 10/25/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "CalculatorViewController.h"

@implementation CalculatorViewController
- (BooleanAlgebraEval *) evaluator{
        if (!evaluator) {
            evaluator = [BooleanAlgebraEval new];
        }
    return evaluator;
}
- (IBAction) buttonPressed:(UIButton *) sender{
    NSString *senderText = [[sender titleLabel]text];
    if ([testText.text isEqual:@"Error!"]) {
        testText.text = @"";
    }
    if ([senderText isEqual:@"Evaluate"]) {
        NSString *value = [[self evaluator] eval:testText.text];
        if (value) {
            testText.text = value;
        } else {
            testText.text = @"Error!";
        }
    } else if([senderText isEqual:@"Clear"]){
        testText.text = @"";
    } else {
        
        testText.text = [testText.text stringByAppendingString:senderText];
    }
}
-(BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return YES;
}
-(void) dealloc{
    [evaluator release];
    [testText release];
    [super dealloc];
}
@end
