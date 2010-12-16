//
//  BooleanAlgebraEval.m
//  BooleanCalculator
//
//  Created by Neal Wiggins on 10/23/10.
//

#import "BooleanAlgebraEval.h"

@implementation BooleanAlgebraEval
- (void) evaluateZeroOne: (NSMutableArray *) stack sub: (NSString *) sub  {
    if (stack.count==0 || [[stack lastObject] isEqual:@"("]) {
        [stack addObject:sub];
        return;
    }
    NSString *top = [stack lastObject];
    [stack removeLastObject];
    if ([top isEqual:@"!"]) {
        NSString *toAdd = nil;
        if ([sub isEqual:@"0"]) {
            toAdd = @"1";
        } else {
            toAdd = @"0";
        }
        [self evaluateZeroOne:stack sub:toAdd];
        
    } else {
        NSString *left = [stack lastObject];
        [stack removeLastObject];
        if([top isEqual:@"+"]){
            if([left isEqual:@"1"] || [sub isEqual:@"1"]){
                [stack addObject:@"1"];
            } else {
                [stack addObject:@"0"];
            }
            
        } else if([top isEqual:@"*"]){
            if([left isEqual:@"0"] || [sub isEqual:@"0"]){
                [stack addObject:@"0"];
            } else {
                [stack addObject:@"1"];
            }
        }
    }
    
}
- (id) eval: (NSString *) input{
    NSMutableArray *stack = [NSMutableArray array];
    for (int i = 0; i< [input length]; i++){
        NSString *sub = [input substringWithRange:(NSMakeRange(i,1))];
        if ([sub isEqual:@"(" ]) {
            [stack addObject:sub];
        } else if([sub isEqual:@")"]){
            if([stack count]<2)
                return nil;
            sub = [stack lastObject];
            if (![sub isEqual:@"0"] && ![sub isEqual:@"1"]) {
                return [@"not right value:" stringByAppendingString:sub];
            }
            [stack removeLastObject];
            if (![[stack lastObject] isEqual:@"("]) {
                return nil;
            }
            [stack removeLastObject];
            if ([stack count]>0  && ([sub isEqual:@"0"] || [sub isEqual:@"1"])) {
                if ([[stack lastObject] isEqual:@"0"] || [[stack lastObject] isEqual:@"1"]) {
                    return nil;
                }
                [self evaluateZeroOne: stack sub: sub];
            } else {
                [stack addObject:sub];
            }
        } else if ([sub isEqual:@"!"]|| [sub isEqual:@"+"] || [sub isEqual:@"*"]) {
            [stack addObject:sub];
        } else if ([sub isEqual:@"0"] || [sub isEqual:@"1"]) {
            if ([[stack lastObject] isEqual:@"0"] || [[stack lastObject] isEqual:@"1"]) {
                return nil;
            }
            [self evaluateZeroOne: stack sub: sub];        
        } else if (![sub isEqual:@" "]){
            return nil;
        }
        
        
    }
    NSString *returner = [stack lastObject];
    if ([stack count] == 1 && ([returner isEqual:@"0"] ||[returner isEqual:@"1"])) {
        return returner;
    }
    return nil;

}
@end
