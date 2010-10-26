//
//  BooleanAlgebraEval.m
//  BooleanCalculator
//
//  Created by Neal Wiggins on 10/23/10.
//

#import "BooleanAlgebraEval.h"

@implementation BooleanAlgebraEval
- (void) evaluateZeroOne: (NSMutableArray *) stack sub: (NSString *) sub  {
    if ([stack count]==0 || [[stack lastObject] isEqualToString:@"("]) {
        [stack addObject:sub];
        return;
    }
    NSString *top = [stack lastObject];
    [stack removeLastObject];
    if ([top isEqualToString:@"!"]) {
        NSString *toAdd = nil;
        if ([sub isEqualToString:@"0"]) {
            toAdd = @"1";
        } else {
            toAdd = @"0";
        }
        [self evaluateZeroOne: stack sub: toAdd];
    } else {
        NSString *left = [stack lastObject];
        [stack removeLastObject];
        if([top isEqualToString:@"+"]){
            if([left isEqualToString:@"1"] || [sub isEqualToString:@"1"]){
                [stack addObject:@"1"];
            } else {
                [stack addObject:@"0"];
            }
            
        } else if([top isEqualToString:@"*"]){
            if([left isEqualToString:@"0"] || [sub isEqualToString:@"0"]){
                [stack addObject:@"0"];
            } else {
                [stack addObject:@"1"];
            }
        }
    }
    
}
- (id) eval: (NSString *) input{
    NSMutableArray *stack = [NSMutableArray new];
    for (int i = 0; i< [input length]; i++){
        NSString *sub = [input substringWithRange:(NSMakeRange(i,1))];
        if ([sub isEqualToString:@"(" ]) {
            [stack addObject:sub];
        } else if([sub isEqualToString:@")"]){
            if([stack count]<2)
                return nil;
            sub = [stack lastObject];
            if (![sub isEqualToString:@"0"] && ![sub isEqualToString:@"1"]) {
                return [@"not right value:" stringByAppendingString:sub];
            }
            [stack removeLastObject];
            if (![[stack lastObject] isEqualToString:@"("]) {
                return nil;
            }
            [stack removeLastObject];
            if ([stack count]>0  &&  ([sub isEqualToString:@"0"] || [sub isEqualToString:@"1"])) {
                if ([[stack lastObject] isEqualToString:@"0"] || [[stack lastObject] isEqualToString:@"1"]) {
                    return nil;
                }
                [self evaluateZeroOne: stack sub: sub];
            } else {
                [stack addObject:sub];
            }
        } else if ([sub isEqualToString:@"!"]|| [sub isEqualToString:@"+"] || [sub isEqualToString:@"*"]) {
            [stack addObject:sub];
        } else if ([sub isEqualToString:@"0"] || [sub isEqualToString:@"1"]) {
            if ([[stack lastObject] isEqualToString:@"0"] || [[stack lastObject] isEqualToString:@"1"]) {
                return nil;
            }
            [self evaluateZeroOne: stack sub: sub];        
        } else if (![sub isEqualToString:@" "]){
            return nil;
        }
        
        
    }
    NSString *returner = [stack lastObject];
    if ([stack count] == 1 && ([returner isEqualToString:@"0"] ||[returner isEqualToString:@"1"])) {
        return returner;
    }
    return nil;

}
@end
