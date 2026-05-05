---
name: Luminous Calm
colors:
  surface: '#f3faff'
  surface-dim: '#c7dde9'
  surface-bright: '#f3faff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#e6f6ff'
  surface-container: '#dbf1fe'
  surface-container-high: '#d5ecf8'
  surface-container-highest: '#cfe6f2'
  on-surface: '#071e27'
  on-surface-variant: '#404752'
  inverse-surface: '#1e333c'
  inverse-on-surface: '#dff4ff'
  outline: '#707883'
  outline-variant: '#bfc7d4'
  surface-tint: '#0061a4'
  primary: '#0061a4'
  on-primary: '#ffffff'
  primary-container: '#2196f3'
  on-primary-container: '#002c4f'
  inverse-primary: '#9ecaff'
  secondary: '#006e1c'
  on-secondary: '#ffffff'
  secondary-container: '#91f78e'
  on-secondary-container: '#00731e'
  tertiary: '#9a25ae'
  on-tertiary: '#ffffff'
  tertiary-container: '#d661e7'
  on-tertiary-container: '#4e005b'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#d1e4ff'
  primary-fixed-dim: '#9ecaff'
  on-primary-fixed: '#001d36'
  on-primary-fixed-variant: '#00497d'
  secondary-fixed: '#94f990'
  secondary-fixed-dim: '#78dc77'
  on-secondary-fixed: '#002204'
  on-secondary-fixed-variant: '#005313'
  tertiary-fixed: '#ffd6fe'
  tertiary-fixed-dim: '#f9abff'
  on-tertiary-fixed: '#35003f'
  on-tertiary-fixed-variant: '#7b008f'
  background: '#f3faff'
  on-background: '#071e27'
  surface-variant: '#cfe6f2'
typography:
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 30px
    fontWeight: '700'
    lineHeight: 38px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-lg:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: 0.01em
  label-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.04em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  margin-mobile: 20px
  gutter: 16px
  stack-sm: 4px
  stack-md: 12px
  stack-lg: 24px
  inset-squish: 12px 16px
  inset-square: 16px
---

## Brand & Style

The brand personality of this design system is centered on gentle encouragement and cognitive ease. It aims to transform the often-stressful nature of habit tracking into a restorative ritual. The UI evokes a sense of "flow"—unobtrusive, lightweight, and deeply satisfying.

The chosen style is **Minimalism** infused with **Soft Tonal Layers**. By prioritizing white space and using high-quality typography, the interface remains breathable. Subtle depth is used not to overwhelm, but to create a tactile sense of progress and accomplishment. The goal is to provide a "quiet" interface that recedes into the background, allowing the user's personal growth to take center stage.

## Colors

The palette utilizes a "functional pastel" approach. **Primary Blue** is reserved for core navigation and focus-related actions. **Secondary Sage Green** is strictly tied to success states, completion, and positive reinforcement. **Tertiary Soft Purple** is used exclusively for "Streak" mechanics and long-term achievements to create a distinct visual reward.

Surfaces use extremely desaturated versions of these hues to categorize tasks without adding visual noise. Backgrounds must remain pure white to maintain the lightweight feel, while text uses a deep slate neutral instead of pure black to soften the reading experience.

## Typography

This design system employs a dual-font strategy to balance friendliness with functional clarity. **Plus Jakarta Sans** is used for headlines to provide a welcoming, optimistic tone. Its slightly rounded terminals complement the overall shape language.

**Inter** is utilized for all body copy and UI labels to ensure maximum legibility and a systematic feel on Android devices. Clear hierarchy is achieved through significant size stepping and weight contrast rather than color, ensuring the interface remains accessible and easy to scan during quick interactions.

## Layout & Spacing

The layout follows a **fluid grid** model optimized for mobile. A standard 4-column grid is used for Android devices with 20px side margins to provide a spacious, premium feel. 

Spacing follows a strict 8px base unit. Component internals use "squished" padding (top/bottom is 75% of left/right) to keep habit cards compact yet touch-friendly. Vertical stacks use 24px of separation between logical groups to maintain the lightweight, uncluttered aesthetic.

## Elevation & Depth

Visual hierarchy is established through **Tonal Layers** and **Ambient Shadows**. Instead of traditional heavy shadows, this design system uses:

1.  **Level 0 (Background):** Pure white, flat.
2.  **Level 1 (Cards/Items):** A subtle 1px border in a tinted neutral (e.g., 10% opacity of the primary color) or a very soft, diffused shadow (Y: 4, Blur: 12, Opacity: 0.05).
3.  **Level 2 (Active/Floating):** Used for the Primary Action Button (FAB) and active modals, featuring a slightly more pronounced shadow with a hint of the primary color’s hue in the shadow itself to create a "glow" effect.

This approach ensures the UI feels tactile and responsive without appearing cluttered.

## Shapes

The shape language is overtly friendly and "squishy." A base roundedness of **16px (1rem)** is applied to standard cards and input fields. Larger containers, such as bottom sheets and featured habit hero cards, use **24px (1.5rem)**. 

Checkboxes and progress bar end-caps are fully rounded (pill-shaped) to reinforce the "satisfying" and "calm" style. Sharp corners are entirely avoided to maintain the organic, motivating atmosphere of the product.

## Components

### Buttons
Primary buttons are high-saturation (Primary Blue) with white text and 24px rounded corners. Secondary buttons use the tinted surface colors (e.g., Surface Blue) with Primary Blue text to indicate lower-priority actions.

### Progress Bars
Bars are thick (min 12px height) with a soft-gray track and a vibrant colored fill. The transition of the fill must be eased (Cubic Bezier) to feel "smooth" and rewarding when a task is completed.

### Checkboxes
Habit completion uses large, circular checkboxes. When unchecked, they feature a 2px stroke in a soft neutral. When checked, they spring to life with a full Sage Green fill and a white checkmark, accompanied by a subtle scale-up animation.

### Streak Badges
These are "High-Contrast" components. They utilize the Tertiary Purple with a slight outer glow and bold white typography to act as a prominent visual hook on the user's profile and habit cards.

### Cards
Habit cards use a Level 1 elevation. They feature a clear horizontal layout: Icon (left), Habit Name/Progress (center), and Checkbox (right). This ensures a consistent thumb-reach pattern for the most common interaction.